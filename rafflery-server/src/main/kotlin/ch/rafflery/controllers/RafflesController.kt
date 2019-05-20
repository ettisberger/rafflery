package ch.rafflery.controllers

import ch.rafflery.domain.raffle.commands.CreateRaffleCommand
import ch.rafflery.infrastructure.CommandBus
import io.ktor.routing.Route
import io.ktor.routing.post
import io.ktor.routing.route

fun Route.raffleRoutes(commandBus: CommandBus) {
  route("/api/raffles") {
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


