package ch.rafflery.domain.commands

import ch.rafflery.domain.ports.RaffleRepository

data class BuySlotsCommand(
    val slots: Array<Int>
) : Command

class BuySlotsCommandHandler(private val raffleRepository: RaffleRepository) :
    CommandHandler<BuySlotsCommand> {

    override fun canHandle(command: Command) = command is BuySlotsCommand

    override fun handle(command: BuySlotsCommand) {
        command.slots.forEach { println(it) }
    }
}
