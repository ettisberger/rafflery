package ch.rafflery.controllers

import ch.rafflery.assertions.shouldBe
import ch.rafflery.domain.commands.BuySlotsCommand
import ch.rafflery.domain.user.User
import io.ktor.http.HttpStatusCode.Companion.OK
import org.junit.jupiter.api.Test

class BuySlotsControllerTest : ControllerTest() {

    private val controller = BuySlotsController(commandBus)

    @Test
    fun `submits BuySlotsCommand`() = testApp(controller) {
        val request = BuySlotsRequest(
            slots = listOf(10, 11, 12)
        )

        val response = putSecure("/api/raffles/1/slots", request)

        val expectedCommand = BuySlotsCommand(
            raffleId = "1",
            user = User("Nichilino Paynolino"),
            slots = listOf(10, 11, 12)
        )

        response shouldHaveStatus OK
        commandBus.commands.size shouldBe 1
        (commandBus.commands[0] as BuySlotsCommand).slots[0] shouldBe expectedCommand.slots[0]
    }
}
