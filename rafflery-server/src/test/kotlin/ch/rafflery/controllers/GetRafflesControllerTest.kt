package ch.rafflery.controllers

import ch.rafflery.domain.ports.RaffleRepository
import ch.rafflery.domain.prize.PrizeItem
import ch.rafflery.domain.raffle.Raffle
import io.ktor.http.HttpMethod
import io.ktor.http.HttpStatusCode
import io.ktor.server.testing.handleRequest
import junit.framework.TestCase.assertEquals
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

  private class FakeRaffleRepository(val raffle: Raffle) : RaffleRepository {
    override fun save(raffle: Raffle) = Unit
    override fun get(id: String): Raffle? = raffle
    override fun getAll(): List<Raffle> = listOf(raffle)
  }

  private val controller = GetRafflesController(FakeRaffleRepository(raffle))

  @Test
  fun `can get raffles`() = testApp(controller) {
    with(handleRequest(HttpMethod.Get, "/api/raffles")) {
      assertEquals(HttpStatusCode.OK, response.status())
      val raffles = response.getBody<List<Raffle>>()
      assertEquals(1, raffles.size)
      assertEquals(raffle, raffles[0])
    }
  }
}
