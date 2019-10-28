package ch.rafflery.domain.commands

import ch.rafflery.domain.ports.RaffleRepository
import ch.rafflery.domain.raffle.Raffle

data class DrawWinnerCommand(
    val raffleId: String,
    val winningSlot: Int
) : Command

class DrawWinnerCommandHandler(
    private val raffleRepository: RaffleRepository
) : CommandHandler<DrawWinnerCommand> {

    override fun canHandle(command: Command) = command is DrawWinnerCommand

    override fun handle(command: DrawWinnerCommand) {
        val raffle: Raffle = raffleRepository.get(command.raffleId)
        val updatedRaffle = raffle.handle(command)
        raffleRepository.save(updatedRaffle)
    }
}
