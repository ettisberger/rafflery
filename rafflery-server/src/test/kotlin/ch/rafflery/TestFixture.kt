package ch.rafflery

import ch.rafflery.domain.ports.IdGenerator
import ch.rafflery.domain.ports.RaffleRepository
import ch.rafflery.domain.raffle.Raffle

interface TestFixture {

    class FakeRaffleRepository(private val raffle: Raffle = aRandomRaffle()) : RaffleRepository {
        val savedRaffles = mutableListOf<Raffle>()

        override fun save(raffle: Raffle) {
            savedRaffles.add(raffle)
        }

        override fun get(id: String): Raffle = raffle
        override fun getAll(): List<Raffle> = listOf(raffle)
    }

    class FakeIdGenerator(private val id: String) : IdGenerator {
        override fun getId(): String = id
    }
}
