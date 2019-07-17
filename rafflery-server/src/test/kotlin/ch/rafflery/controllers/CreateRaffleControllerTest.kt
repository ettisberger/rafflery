package ch.rafflery.controllers

import ch.rafflery.assertions.shouldBe
import ch.rafflery.domain.commands.CreateRaffleCommand
import io.ktor.http.HttpStatusCode.Companion.OK
import io.ktor.http.HttpStatusCode.Companion.Unauthorized
import org.junit.Test

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
        FakeCommandBus.commands.size shouldBe 1
        FakeCommandBus.commands[0] shouldBe expectedCommand
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
