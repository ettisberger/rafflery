// ktlint-disable filename
package ch.rafflery.plugins

import ch.rafflery.domain.ports.RaffleRepository
import ch.rafflery.domain.prize.PrizeItem
import ch.rafflery.domain.raffle.Raffle
import ch.rafflery.domain.raffle.Ticket

class StubbedRaffleRepository : RaffleRepository {
    // test data
    private val rolexRaffle = Raffle(
        "Rolex GMT Master II",
        PrizeItem("Rolex GMT Master II", 10000),
        100,
        listOf(Ticket("Andy"), Ticket("Nicholas")),
        "ninula",
        "1"
    )

    private val sofaRaffle = Raffle(
        "Sofa Vitra",
        PrizeItem("Sofa Vitra", 5000),
        100,
        listOf(Ticket("Daniel"), Ticket("Nicholas")),
        "ninula2",
        "2"
    )

    private val mainboardRaffle = Raffle(
        "ASUS Mainboard GTX403",
        PrizeItem("ASUS Mainboard GTX403", 400),
        20,
        listOf(Ticket("Susi"), Ticket("Nicholas")),
        "ninula3",
        "3"
    )

    private val pandaRaffle = Raffle(
        "Pandabär",
        PrizeItem("Pandabär", 2000),
        40,
        listOf(Ticket("Andy"), Ticket("Nicole")),
        "ninula4",
        "4"
    )

    private val raffles: MutableList<Raffle> =
        mutableListOf(rolexRaffle, sofaRaffle, mainboardRaffle, pandaRaffle)

    override fun getAll(): List<Raffle> =
        raffles.map { it.copy() }

    override fun save(raffle: Raffle) {
        raffles.add(raffle.copy(id = "stubbed_id"))
    }

    override fun get(id: String): Raffle? =
        raffles.find { it.id == id }?.let { it.copy() }
}
