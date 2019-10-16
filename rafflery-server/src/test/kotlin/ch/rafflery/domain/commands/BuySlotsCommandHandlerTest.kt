package ch.rafflery.domain.commands

/* ktlint-disable import-ordering */
import ch.rafflery.TestFixture
import ch.rafflery.TestFixture.FakeRaffleRepository
import ch.rafflery.aRandomRaffle
import ch.rafflery.domain.raffle.Ticket
import ch.rafflery.domain.user.User
import org.junit.jupiter.api.Assertions.assertThrows
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals
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

    @Test
    fun `buys raffle slots`() {
        val raffleRepository = FakeRaffleRepository(aRandomRaffle())
        val commandHandler = BuySlotsCommandHandler(raffleRepository)
        val command = BuySlotsCommand(
            raffleId = "1",
            user = User("NDY"),
            slots = listOf(10, 11, 12)
        )

        commandHandler.handle(command)

        val updatedRaffle = raffleRepository.savedRaffles.first()
        assertEquals(5, updatedRaffle.purchasedTickets.size)
    }

    @Test
    fun `purchasing already purchased slots throws exception`() {
        val raffle = aRandomRaffle().copy(
            purchasedTickets = listOf(
                Ticket(owner = "owner", slotNumber = 1)
            )
        )

        val raffleRepository = FakeRaffleRepository(raffle)
        val commandHandler = BuySlotsCommandHandler(raffleRepository)
        val command = BuySlotsCommand(
            raffleId = raffle.id,
            user = User("NDY"),
            slots = listOf(1)
        )

        assertThrows(IllegalArgumentException::class.java) {
            commandHandler.handle(command)
        }

    }
}
