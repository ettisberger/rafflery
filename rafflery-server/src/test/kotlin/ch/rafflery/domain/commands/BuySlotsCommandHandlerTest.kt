package ch.rafflery.domain.commands

/* ktlint-disable import-ordering */
import ch.rafflery.TestFixture
import ch.rafflery.TestFixture.FakeRaffleRepository
import ch.rafflery.domain.user.User
import org.junit.jupiter.api.Test
import kotlin.test.assertTrue

internal class BuySlotsCommandHandlerTest : TestFixture {

    @Test
    fun `can handle a BuySlotsCommand`() {
        val raffleRepository = FakeRaffleRepository()
        val commandHandler = BuySlotsCommandHandler(raffleRepository)
        val command = BuySlotsCommand(
            raffleId = "1",
            user = User("NDY"),
            slots = listOf(10, 11, 12)
        )

        assertTrue { commandHandler.canHandle(command) }
    }
}
