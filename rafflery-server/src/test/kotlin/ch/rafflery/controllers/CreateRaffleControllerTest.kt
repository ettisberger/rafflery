package ch.rafflery.controllers

import ch.rafflery.domain.commands.CreateRaffleCommand
import io.ktor.http.HttpStatusCode.Companion.OK
import io.ktor.http.HttpStatusCode.Companion.Unauthorized
import org.junit.Test
import kotlin.test.assertEquals

class CreateRaffleControllerTest : ControllerTest() {

  private val controller = CreateRaffleController(FakeCommandBus)

  @Test
  fun `submits CreateRaffleCommand`() = testApp(controller) {
    val request = CreateRaffleRequest(
      name = "name",
      itemName = "itemName",
      itemValue = 10,
      slotSize = 10,
      createdBy = "andy"
    )

    val response = postSecure("/api/raffles", request)

    val expectedCommand = CreateRaffleCommand(
      name = "name",
      itemName = "itemName",
      itemValue = 10,
      slotSize = 10,
      createdBy = "andy"
    )

    response shouldHaveStatus OK
    assertEquals(1, FakeCommandBus.commands.size)
    assertEquals(expectedCommand, FakeCommandBus.commands[0])
  }

  @Test
  fun `does not allow unauthenticated user to create a raffle`() = testApp(controller) {
    val request = CreateRaffleRequest(
      name = "name",
      itemName = "itemName",
      itemValue = 10,
      slotSize = 10,
      createdBy = "andy"
    )

    val response = post("/api/raffles", request)
    response shouldHaveStatus Unauthorized
  }
}
