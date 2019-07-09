package ch.rafflery.controllers

import ch.rafflery.domain.ports.RaffleRepository
import ch.rafflery.domain.prize.PrizeItem
import ch.rafflery.domain.raffle.Raffle
import io.ktor.http.HttpStatusCode
import org.junit.Test

class GetRafflesControllerTest : ControllerTest() {

  val raffle = Raffle(
    name = "name",
    item = PrizeItem("name", 10),
    slotSize = 10,
    purchasedTickets = listOf(),
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
    val response = getSecure("api/raffles")

    response shouldHaveStatus HttpStatusCode.OK
    response shouldHaveBody listOf(raffle)
  }

}
