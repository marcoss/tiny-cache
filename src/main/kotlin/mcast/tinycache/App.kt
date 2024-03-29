package tinycache

import io.ktor.http.*
import io.ktor.serialization.kotlinx.json.*
import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import io.ktor.server.plugins.contentnegotiation.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

const val DEFAULT_CACHE_CAPACITY = 100
const val BASE_API_ROUTE = "/api/v1/tinycache"

fun main(args: Array<String>) {
    val cacheCapacity = args.getOrNull(0)?.toIntOrNull() ?: DEFAULT_CACHE_CAPACITY

    embeddedServer(Netty, port = 8080) { module(cacheCapacity) }.start(wait = true)
}

fun Application.module(cacheCapacity: Int) {
    log.info("Starting server with capacity of ${cacheCapacity}...")

    install(ContentNegotiation) { json() }

    val cache = TinyCache<String, String>(cacheCapacity)

    routing {
        route(BASE_API_ROUTE) {
            put {
                try {
                    val payload = call.receive<CacheRequest>()

                    call.application.environment.log.info(
                            "cache put: ${payload.key} -> ${payload.value}"
                    )

                    cache.put(payload.key, payload.value)

                    call.respond(HttpStatusCode.OK)
                } catch (e: Exception) {
                    // TODO: Move to status page and reduce exception class scope
                    call.application.environment.log.error("Failed to parse request: ", e)
                    call.respond(HttpStatusCode.BadRequest)
                }
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
