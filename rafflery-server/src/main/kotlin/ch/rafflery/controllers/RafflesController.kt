package ch.rafflery.controllers

import ch.rafflery.domain.raffle.commands.CreateRaffleCommand
import ch.rafflery.infrastructure.CommandBus
import io.ktor.http.ContentType
import io.ktor.application.call
import io.ktor.response.respondText
import io.ktor.routing.Route
import io.ktor.routing.get
import io.ktor.routing.post
import io.ktor.routing.route

fun Route.raffleRoutes(commandBus: CommandBus) {
    route("/api/raffles") {
        get("/") {
            call.respondText("Get Raffles", ContentType.Text.Plain)
        }
        post("/") {
            val command = CreateRaffleCommand(
                    "hello",
                    "world",
                    2,
                    10,
                    "hi"
            )

            commandBus.submit(command)
        }
    }
}


