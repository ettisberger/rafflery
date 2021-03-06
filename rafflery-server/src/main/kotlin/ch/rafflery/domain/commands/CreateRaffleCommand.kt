package ch.rafflery.domain.commands

import ch.rafflery.domain.ports.RaffleRepository
import ch.rafflery.domain.raffle.Raffle

data class CreateRaffleCommand(
    val id: String,
    val name: String,
    val itemName: String,
    val itemValue: Int,
    val slotSize: Int,
    val createdBy: String
) : Command

class CreateRaffleCommandHandler(private val raffleRepository: RaffleRepository) :
    CommandHandler<CreateRaffleCommand> {

    override fun canHandle(command: Command) = command is CreateRaffleCommand

    override fun handle(command: CreateRaffleCommand) =
        Raffle(command).let(raffleRepository::save)
}
