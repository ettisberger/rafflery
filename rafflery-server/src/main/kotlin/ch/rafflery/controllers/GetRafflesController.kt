package ch.rafflery.controllers

import ch.rafflery.domain.ports.RaffleRepository
import io.ktor.application.ApplicationCall
import io.ktor.response.respond

class GetRafflesController(
  private val raffleRepository: RaffleRepository
): SecureController() {

  override val method = "GET"
  override val path = "/api/raffles"

  override suspend fun invoke(call: ApplicationCall) {
    call.respond(raffleRepository.getAll())
  }
}
