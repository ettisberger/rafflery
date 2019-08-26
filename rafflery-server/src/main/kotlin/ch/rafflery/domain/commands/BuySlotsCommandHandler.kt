package ch.rafflery.domain.commands

import ch.rafflery.domain.ports.RaffleRepository
import ch.rafflery.domain.raffle.Raffle
import ch.rafflery.domain.raffle.Ticket
import ch.rafflery.domain.user.User

data class BuySlotsCommand(
    val raffleId: String,
    val user: User,
    val slots: Array<Int>
) : Command

class BuySlotsCommandHandler(private val raffleRepository: RaffleRepository) :
    CommandHandler<BuySlotsCommand> {

    override fun canHandle(command: Command) = command is BuySlotsCommand

    override fun handle(command: BuySlotsCommand) {
        val raffleId = command.raffleId
        val user = command.user
        val slots = command.slots

        val raffle: Raffle? = getRaffle(raffleId)

        if (raffle != null && slots != null) {
            buySlots(raffle, user, slots)
        } else {
            // error handling
        }
    }

    private fun getRaffle(raffleId: String): Raffle? {
        return raffleRepository.get(raffleId)
    }

    private fun buySlots(
        raffle: Raffle,
        user: User,
        slots: Array<Int>
    ) {
        val purchasedTickets: MutableList<Ticket> = raffle.purchasedTickets

        slots.forEach { slot ->
            val ticket: Ticket? = purchasedTickets.find { it.slot == slot }

            if (ticket == null) {
                purchasedTickets.add(Ticket(user.name, slot))
                println("Slot $slot purchased and added. Purchased slots: ${purchasedTickets.size}")
            } else {
                // cant buy already purchased ticket
                // error handling
                println("Cant buy slot. Already purchased")
            }
        }
    }
}
