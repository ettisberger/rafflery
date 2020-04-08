// ktlint-disable filename
package ch.rafflery.plugins

import ch.rafflery.domain.ports.RaffleRepository
import ch.rafflery.domain.prize.PrizeItem
import ch.rafflery.domain.raffle.Raffle
import ch.rafflery.domain.raffle.Ticket

class StubbedRaffleRepository : RaffleRepository {
    // test data
    private val rolexRaffle = Raffle(
        name = "Rolex GMT Master II",
        item = PrizeItem("Rolex GMT Master II", 10000),
        slotSize = 100,
        purchasedTickets = mutableListOf(Ticket("Andy", 1), Ticket("Nicholas", 2)),
        createdBy = "ninula",
        id = "1",
        winner = null,
        winningSlot = null
    )

    private val sofaRaffle = Raffle(
        name = "Sofa Vitra",
        item = PrizeItem("Sofa Vitra", 5000),
        slotSize = 100,
        purchasedTickets = mutableListOf(Ticket("Daniel", 5), Ticket("Nicholas", 10)),
        createdBy = "ninula2",
        id = "2",
        winner = null,
        winningSlot = null
    )

    private val mainboardRaffle = Raffle(
        name = "ASUS Mainboard GTX403",
        item = PrizeItem("ASUS Mainboard GTX403", 400),
        slotSize = 20,
        purchasedTickets = mutableListOf(Ticket("Susi", 20), Ticket("Nicholas", 7)),
        createdBy = "ninula3",
        id = "3",
        winner = null,
        winningSlot = null
    )

    private val pandaRaffle = Raffle(
        name = "Pandabär",
        item = PrizeItem("Pandabär", 2000),
        slotSize = 40,
        purchasedTickets = mutableListOf(Ticket("Andy", 30), Ticket("Nicole", 40)),
        createdBy = "ninula4",
        id = "4",
        winner = null,
        winningSlot = null
    )

    private val raffles: MutableList<Raffle> =
        mutableListOf(rolexRaffle, sofaRaffle, mainboardRaffle, pandaRaffle)

    override fun getAll(): List<Raffle> =
        raffles.map { it.copy() }

    override fun save(raffle: Raffle) {
        val existingRaffle = raffles.find { it.id == raffle.id }

        if (existingRaffle == null) {
            raffles.add(raffle)
        } else {
            raffles[raffles.indexOf(existingRaffle)] = raffle
        }
    }

    override fun get(id: String): Raffle =
        raffles.find { it.id == id }?.let { it.copy() }
            ?: throw IllegalArgumentException("No raffle with id $id found.")
}
