package ch.rafflery.domain.commands

/* ktlint-disable import-ordering */
import ch.rafflery.TestFixture
import ch.rafflery.TestFixture.FakeRaffleRepository
import ch.rafflery.domain.prize.PrizeItem
import ch.rafflery.domain.raffle.Raffle
import org.junit.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

internal class BuySlotsCommandHandlerTest : TestFixture {

    @Test
    fun `can handle a BuySlotsCommand`() {
        val raffleRepository = FakeRaffleRepository()
        val commandHandler = BuySlotsCommandHandler(raffleRepository)
        val command = BuySlotsCommand(arrayOf(10,11,12))

        assertTrue { commandHandler.canHandle(command) }
    }
}
