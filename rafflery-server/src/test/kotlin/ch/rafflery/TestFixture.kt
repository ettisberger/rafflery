package ch.rafflery

import ch.rafflery.domain.commands.CreateRaffleCommand
import ch.rafflery.domain.ports.RaffleRepository
import ch.rafflery.domain.raffle.Raffle

interface TestFixture {

  class FakeRaffleRepository(val raffle: Raffle? = null) : RaffleRepository {
    private val raffles = mutableListOf<Raffle>()

    override fun save(raffle: Raffle) { raffles.add(raffle) }
    override fun get(id: String): Raffle? = raffle
    override fun getAll(): List<Raffle> = raffles
  }

  fun aRandomCreateRaffleCommand() =
    CreateRaffleCommand(
      name = "name",
      itemName = "itemName",
      itemValue = 10,
      slotSize = 10,
      createdBy = "gomph"
    )

}
