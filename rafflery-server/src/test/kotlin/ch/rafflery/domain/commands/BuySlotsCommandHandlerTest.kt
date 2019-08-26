package ch.rafflery.domain.commands

/* ktlint-disable import-ordering */
import ch.rafflery.TestFixture
import ch.rafflery.TestFixture.FakeRaffleRepository
import ch.rafflery.domain.prize.PrizeItem
import ch.rafflery.domain.raffle.Raffle
import ch.rafflery.domain.raffle.Ticket
import ch.rafflery.domain.user.User
import org.junit.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

internal class BuySlotsCommandHandlerTest : TestFixture {

    @Test
    fun `can handle a BuySlotsCommand`() {
        val raffleRepository = FakeRaffleRepository()
        val commandHandler = BuySlotsCommandHandler(raffleRepository)
        val command = BuySlotsCommand("1", User("NDY"), arrayOf(10, 11, 12))

        assertTrue { commandHandler.canHandle(command) }
    }

    @Test
    fun `buys raffle slots`() {
        val rolexRaffle = Raffle(
            "Rolex GMT Master II",
            PrizeItem("Rolex GMT Master II", 10000),
            100,
            mutableListOf(Ticket("Andy", 1), Ticket("Nicholas", 2)),
            "ninula",
            "1"
        )

        val raffleRepository = FakeRaffleRepository(rolexRaffle)
        val commandHandler = BuySlotsCommandHandler(raffleRepository)
        val command = BuySlotsCommand("1", User("NDY"), arrayOf(10, 11, 12))

        assertEquals(2, raffleRepository.get("1")?.purchasedTickets?.size)

        commandHandler.handle(command)

        assertEquals(5, raffleRepository.get("1")?.purchasedTickets?.size)
    }
}
