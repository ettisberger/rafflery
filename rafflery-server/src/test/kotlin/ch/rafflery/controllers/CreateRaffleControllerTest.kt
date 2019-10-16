package ch.rafflery.controllers

import ch.rafflery.TestFixture
import ch.rafflery.TestFixture.FakeIdGenerator
import ch.rafflery.assertions.shouldBe
import ch.rafflery.domain.commands.CreateRaffleCommand
import io.ktor.http.HttpStatusCode.Companion.OK
import io.ktor.http.HttpStatusCode.Companion.Unauthorized
import org.junit.jupiter.api.Test

class CreateRaffleControllerTest : ControllerTest(), TestFixture {

    private val mockId = "1"
    private val idGenerator = FakeIdGenerator(id = mockId)

    private val controller = CreateRaffleController(commandBus, idGenerator)

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
            id = mockId,
            name = "name",
            itemName = "itemName",
            itemValue = 10,
            slotSize = 10,
            createdBy = "andy"
        )

        response shouldHaveStatus OK
        commandBus.commands.size shouldBe 1
        commandBus.commands[0] shouldBe expectedCommand
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
