package ch.rafflery.controllers

import ch.rafflery.domain.ports.RaffleRepository
import ch.rafflery.domain.prize.PrizeItem
import ch.rafflery.domain.raffle.Raffle
import io.ktor.http.HttpStatusCode.Companion.OK
import org.junit.Test

class GetRafflesControllerTest : ControllerTest() {

    val raffle = Raffle(
        name = "name",
        item = PrizeItem("name", 10),
        slotSize = 10,
        purchasedTickets = mutableListOf(),
        createdBy = "nick",
        id = "1"
    )

    private val raffleRepository = object : RaffleRepository {
        override fun save(raffle: Raffle) = Unit
        override fun get(id: String): Raffle? = raffle
        override fun getAll(): List<Raffle> = listOf(raffle)
    }

    private val controller = GetRafflesController(raffleRepository)

    @Test
    fun `can get raffles`() = testApp(controller) {
        val response = get("api/raffles")

        response shouldHaveStatus OK
        response shouldHaveBody listOf(raffle)
    }
}
