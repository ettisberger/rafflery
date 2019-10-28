package ch.rafflery.domain.commands

import ch.rafflery.domain.ports.RaffleRepository
import ch.rafflery.domain.raffle.Raffle
import ch.rafflery.domain.user.User

data class BuySlotsCommand(
    val raffleId: String,
    val user: User,
    val slots: List<Int>
) : Command

fun validate(command: BuySlotsCommand, raffle: Raffle) {
    val purchasedSlots: List<Int> = raffle.purchasedTickets.map { it.slotNumber }
    val conflicts: Set<Int> = command.slots.intersect(purchasedSlots)

    require(conflicts.isEmpty()) {
        "Slots ${conflicts.joinToString()} for raffle ${raffle.id} have already been purchased."
    }
}

class BuySlotsCommandHandler(
    private val raffleRepository: RaffleRepository
) : CommandHandler<BuySlotsCommand> {

    override fun canHandle(command: Command) = command is BuySlotsCommand

    override fun handle(command: BuySlotsCommand) {
        val raffle: Raffle = raffleRepository.get(command.raffleId)
        val updatedRaffle = raffle.handle(command)
        raffleRepository.save(updatedRaffle)
    }
}
