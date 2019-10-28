package ch.rafflery.domain.commands

/* ktlint-disable import-ordering */
import ch.rafflery.TestFixture
import ch.rafflery.TestFixture.FakeRaffleRepository
import ch.rafflery.aRandomCreateRaffleCommand
import org.junit.jupiter.api.Test
import kotlin.test.assertTrue

internal class CreateRaffleCommandHandlerTest : TestFixture {

    @Test
    fun `can handle a CreateRaffleCommand`() {
        val raffleRepository = FakeRaffleRepository()
        val commandHandler = CreateRaffleCommandHandler(raffleRepository)
        val command = aRandomCreateRaffleCommand()

        assertTrue { commandHandler.canHandle(command) }
    }
}
