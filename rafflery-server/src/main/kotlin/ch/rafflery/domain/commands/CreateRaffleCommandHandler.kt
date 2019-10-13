package ch.rafflery.domain.commands

import ch.rafflery.domain.ports.RaffleRepository
import ch.rafflery.domain.prize.PrizeItem
import ch.rafflery.domain.raffle.Raffle
import java.util.*

data class CreateRaffleCommand(
    val name: String,
    val itemName: String,
    val itemValue: Int,
    val slotSize: Int,
    val createdBy: String
) : Command

class CreateRaffleCommandHandler(private val raffleRepository: RaffleRepository) :
    CommandHandler<CreateRaffleCommand> {

    override fun canHandle(command: Command) = command is CreateRaffleCommand

    override fun handle(command: CreateRaffleCommand) {
        val raffle = Raffle(
            name = command.name,
            item = PrizeItem(command.itemName, command.itemValue),
            slotSize = command.slotSize,
            purchasedTickets = mutableListOf(),
            createdBy = command.createdBy,
            id = Date().toString()
        )

        raffleRepository.save(raffle)
    }
}
