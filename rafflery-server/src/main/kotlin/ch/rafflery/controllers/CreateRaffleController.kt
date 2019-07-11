package ch.rafflery.controllers

import ch.rafflery.domain.commands.CreateRaffleCommand
import ch.rafflery.infrastructure.CommandBus
import io.ktor.application.ApplicationCall
import io.ktor.http.HttpStatusCode
import io.ktor.request.receive

class CreateRaffleController(
  private val commandBus: CommandBus
): SecureController() {

  override val method = "POST"
  override val path = "/api/raffles"

  override suspend fun invoke(call: ApplicationCall) {
    val request = call.receive<CreateRaffleRequest>()
    val command = CreateRaffleCommand(
      request.name,
      request.itemName,
      request.itemValue,
      request.slotSize,
      request.createdBy
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
