package ch.rafflery.domain.commands

/* ktlint-disable import-ordering */
import ch.rafflery.TestFixture
import ch.rafflery.TestFixture.FakeRaffleRepository
import ch.rafflery.domain.prize.PrizeItem
import ch.rafflery.domain.raffle.Raffle
import org.junit.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

internal class CreateRaffleCommandHandlerTest : TestFixture {

    @Test
    fun `can handle a CreateRaffleCommand`() {
        val raffleRepository = FakeRaffleRepository()
        val commandHandler = CreateRaffleCommandHandler(raffleRepository)
        val command = aRandomCreateRaffleCommand()

        assertTrue { commandHandler.canHandle(command) }
    }

    @Test
    fun `creates new raffle`() {
        val raffleRepository = FakeRaffleRepository()
        val commandHandler = CreateRaffleCommandHandler(raffleRepository)
        val command = aRandomCreateRaffleCommand()

        commandHandler.handle(command)

        val expectedRaffle = Raffle(
            name = command.name,
            item = PrizeItem(command.itemName, command.itemValue),
            slotSize = command.slotSize,
            purchasedTickets = emptyList(),
            createdBy = command.createdBy
        )

        assertEquals(1, raffleRepository.getAll().size)
        assertEquals(expectedRaffle, raffleRepository.getAll()[0])
    }
}
