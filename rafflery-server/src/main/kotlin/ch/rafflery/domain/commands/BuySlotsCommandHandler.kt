package ch.rafflery.domain.commands

import ch.rafflery.domain.ports.RaffleRepository
import ch.rafflery.domain.raffle.Raffle
import ch.rafflery.domain.raffle.Ticket
import ch.rafflery.domain.user.User

data class BuySlotsCommand(
    val raffleId: String,
    val user: User,
    val slots: List<Int>
) : Command

class BuySlotsCommandHandler(
    private val raffleRepository: RaffleRepository
) : CommandHandler<BuySlotsCommand> {

    override fun canHandle(command: Command) = command is BuySlotsCommand

    override fun handle(command: BuySlotsCommand) {
        val raffleId = command.raffleId
        val user = command.user
        val slots = command.slots

        val raffle: Raffle = raffleRepository.get(raffleId)
        val updatedRaffle = buySlots(raffle, user, slots)
        raffleRepository.save(updatedRaffle)
    }

    private fun buySlots(
        raffle: Raffle,
        user: User,
        slotsToPurchase: List<Int>
    ): Raffle {
        val purchasedSlots: List<Int> = raffle.purchasedTickets.map { it.slotNumber }
        val conflicts: Set<Int> = slotsToPurchase.intersect(purchasedSlots)

        require(conflicts.isEmpty()) {
            "Slots ${conflicts.joinToString()} for raffle ${raffle.id} have already been purchased."
        }

        val newTickets = slotsToPurchase.map { Ticket(user.name, it) }

        return raffle.copy(
            purchasedTickets = raffle.purchasedTickets + newTickets
        )
    }
}
