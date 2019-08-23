package ch.rafflery.controllers

import ch.rafflery.domain.commands.BuySlotsCommand
import ch.rafflery.infrastructure.CommandBus
import io.ktor.application.ApplicationCall
import io.ktor.http.HttpStatusCode
import io.ktor.request.receive

class BuySlotsController(
    private val commandBus: CommandBus
) : SecureController() {

    override val method = "PUT"
    override val path = "/api/raffles/{id}/slots"

    override suspend fun invoke(call: ApplicationCall) {
        // TODO map to BuySlotsRequest
        val request = call.receive<Array<Int>>()
        val command = BuySlotsCommand(request)

        commandBus.submit(command)

        call.response.status(HttpStatusCode.OK)
    }
}
