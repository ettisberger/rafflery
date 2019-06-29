package ch.rafflery.plugins

import ch.rafflery.domain.ports.RaffleRepository
import ch.rafflery.domain.raffle.Raffle

class StubbedRaffleRepository : RaffleRepository {

  private val raffles: MutableList<Raffle> = mutableListOf()

  override fun getAll(): List<Raffle> =
    raffles.map { it.copy() }

  override fun save(raffle: Raffle) {
    raffles.add(raffle.copy(id = "stubbed_id"))
  }

  override fun get(id: String): Raffle? =
    raffles.find { it.id == id }?.let { it.copy() }


}
