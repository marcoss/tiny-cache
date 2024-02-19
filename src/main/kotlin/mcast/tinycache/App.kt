package tinycache

import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun main() {
    val cache = TinyCache<Long, String>(10)

    embeddedServer(Netty, port = 8080) {
                routing {
                    get("/") {
                        var ip = call.request.local.remoteHost
                        cache.put(System.currentTimeMillis(), ip)
                        call.respondText(cache.size().toString())
                        println("Debug: $cache")
                    }
                }
            }
            .start(wait = true)
}
