package ch.rafflery.controllers

import ch.rafflery.assertions.shouldBe
import ch.rafflery.domain.commands.BuySlotsCommand
import ch.rafflery.domain.user.User
import io.ktor.http.HttpStatusCode.Companion.OK
import org.junit.Test

class BuySlotsControllerTest : ControllerTest() {

    private val controller = BuySlotsController(FakeCommandBus)

    @Test
    fun `submits BuySlotsCommand`() = testApp(controller) {
        val request = listOf(10, 11, 12)

        val response = putSecure("/api/savedRaffles/1/slots", request)

        val expectedCommand = BuySlotsCommand("1", User("Nichilino Paynolino"), request)

        response shouldHaveStatus OK
        FakeCommandBus.commands.size shouldBe 1
        (FakeCommandBus.commands[0] as BuySlotsCommand).slots[0] shouldBe expectedCommand.slots[0]
    }
}
