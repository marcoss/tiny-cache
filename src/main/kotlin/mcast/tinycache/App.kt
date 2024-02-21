package tinycache

// import io.ktor.util.logging.*
import io.ktor.http.*
import io.ktor.serialization.kotlinx.json.*
import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import io.ktor.server.plugins.contentnegotiation.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import kotlinx.serialization.Serializable

fun main() {
    embeddedServer(Netty, port = 8080) { module() }.start(wait = true)
}

fun Application.module() {
    log.info("Starting server...")

    install(ContentNegotiation) { json() }

    val cache = TinyCache<String, String>(100)

    @Serializable data class CacheRequest(val key: String, val value: String)

    routing {
        route("/api/v1/tinycache") {
            put {
                val payload = call.receive<CacheRequest>()

                call.application.environment.log.info(
                        "cache put: ${payload.key} -> ${payload.value}"
                )

                cache.put(payload.key, payload.value)

                call.respond(HttpStatusCode.OK)
            }

            get("/{key}") {
                val key = call.parameters["key"]

                if (key.isNullOrEmpty()) {
                    call.respond(HttpStatusCode.BadRequest)
                    return@get
                }

                val value = cache.get(key)

                if (value != null) {
                    call.application.environment.log.info("cache get: ${key} -> ${value}")
                    call.respond(HttpStatusCode.OK, value)
                } else {
                    call.application.environment.log.info("cache miss: ${key}")
                    call.respond(HttpStatusCode.NotFound)
                }
            }
        }
    }
}
