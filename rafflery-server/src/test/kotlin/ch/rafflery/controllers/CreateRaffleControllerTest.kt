package ch.rafflery.controllers

import ch.rafflery.domain.commands.CreateRaffleCommand
import org.junit.Ignore
import org.junit.Test
import kotlin.test.assertEquals

class CreateRaffleControllerTest : ControllerTest() {

  private val controller = CreateRaffleController(FakeCommandBus)

  @Ignore
  @Test
  fun `submits CreateRaffleCommand`() = testApp(controller) {
    val request = CreateRaffleRequest(
      name = "name",
      itemName = "itemName",
      itemValue = 10,
      slotSize = 10,
      createdBy = "andy"
    )

    post("/api/raffles", request) {
      val expectedCommand = CreateRaffleCommand(
        name = "name",
        itemName = "itemName",
        itemValue = 10,
        slotSize = 10,
        createdBy = "andy"
      )
      assertEquals(1, FakeCommandBus.commands.size)
      assertEquals(expectedCommand, FakeCommandBus.commands[0])
    }
  }
}
