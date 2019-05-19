package ch.rafflery.app

import ch.rafflery.domain.commands.CreateRaffleCommand
import ch.rafflery.domain.commands.CreateRaffleCommandHandler
import ch.rafflery.domain.ports.RaffleRepository
import ch.rafflery.domain.raffle.Raffle
import ch.rafflery.plugins.StubbedRaffleRepository
import io.ktor.application.call
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

class App @Inject constructor(
    private val commandHandler: CreateRaffleCommandHandler,
    private val raffleRepository: RaffleRepository
) {

    fun start() {
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

        val command = CreateRaffleCommand(
            "hello",
            "world",
            2,
            10,
            "hi"
        )

        commandHandler.handle(command)

        val raffle: Raffle? = raffleRepository.get("stubbed_id")
        raffle.let { println("Raffle was added to the database") }
        server.start(wait = true)
    }
}

