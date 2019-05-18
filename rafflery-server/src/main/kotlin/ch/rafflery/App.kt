package ch.rafflery

import io.ktor.application.call
import io.ktor.http.ContentType
import io.ktor.http.content.*
import io.ktor.response.respondFile
import io.ktor.response.respondText
import io.ktor.routing.get
import io.ktor.routing.routing
import io.ktor.server.engine.embeddedServer
import io.ktor.server.netty.Netty
import java.io.File

const val STATIC_ROUTE = "./rafflery-ui/build/"

fun main() {
    val server = embeddedServer(Netty, port = 8080) {
        routing {
            get("/") {
                call.respondFile(File("$STATIC_ROUTE/index.html"))
            }
            get("/api/hello") {
                call.respondText("Hello World!", ContentType.Text.Plain)
            }
            static {
                files(STATIC_ROUTE)
            }
        }
    }
    server.start(wait = true)
}
