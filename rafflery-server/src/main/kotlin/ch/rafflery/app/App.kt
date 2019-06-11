package ch.rafflery.app

import ch.rafflery.controllers.raffleRoutes
import ch.rafflery.infrastructure.CommandBus
import io.ktor.application.call
import io.ktor.application.install
import io.ktor.features.CallLogging
import io.ktor.http.ContentType
import io.ktor.http.content.files
import io.ktor.http.content.static
import io.ktor.response.respondFile
import io.ktor.response.respondText
import io.ktor.routing.get
import io.ktor.routing.routing
import io.ktor.server.engine.embeddedServer
import io.ktor.server.netty.Netty
import java.io.File
import javax.inject.Inject

const val STATIC_ROUTE = "./rafflery-ui/build/"

class App @Inject constructor(private val commandBus: CommandBus) {

    fun start() {
        val server = embeddedServer(Netty, port = 8080) {

            install(CallLogging) // log every call

            routing {
                raffleRoutes(commandBus)
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
}

