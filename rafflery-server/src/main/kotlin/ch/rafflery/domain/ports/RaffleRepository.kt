package ch.rafflery.domain.ports

import ch.rafflery.domain.raffle.Raffle

interface RaffleRepository {
    fun save(raffle: Raffle)

    fun get(id: String): Raffle?

    fun getAll(): List<Raffle>
}
