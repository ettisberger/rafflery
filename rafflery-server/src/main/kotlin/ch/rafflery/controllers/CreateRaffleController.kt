package ch.rafflery.controllers

import ch.rafflery.domain.commands.CreateRaffleCommand
import ch.rafflery.domain.ports.IdGenerator
import ch.rafflery.infrastructure.CommandBus
import io.ktor.application.ApplicationCall
import io.ktor.http.HttpStatusCode
import io.ktor.request.receive

class CreateRaffleController(
    private val commandBus: CommandBus,
    private val idGenerator: IdGenerator
) : SecureController() {

    override val method = "POST"
    override val path = "/api/raffles"

    override suspend fun invoke(call: ApplicationCall) {
        val request = call.receive<CreateRaffleRequest>()

        val command = CreateRaffleCommand(
            id = idGenerator.getId(),
            name = request.name,
            itemName = request.itemName,
            itemValue = request.itemValue,
            slotSize = request.slotSize,
            createdBy = request.createdBy
        )

        commandBus.submit(command)

        call.response.status(HttpStatusCode.OK)
    }
}

data class CreateRaffleRequest(
    val name: String,
    val itemName: String,
    val itemValue: Int,
    val slotSize: Int,
    val createdBy: String
)
