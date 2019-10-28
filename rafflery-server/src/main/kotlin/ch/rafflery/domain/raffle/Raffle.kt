package ch.rafflery.domain.raffle

import ch.rafflery.domain.commands.BuySlotsCommand
import ch.rafflery.domain.commands.Command
import ch.rafflery.domain.commands.validate
import ch.rafflery.domain.prize.PrizeItem

data class Raffle(
  val name: String,
  val item: PrizeItem,
  val slotSize: Int,
  val purchasedTickets: List<Ticket>,
  val createdBy: String,
  val id: String
) {

  fun handle(command: Command): Raffle =
    when (command) {
      is BuySlotsCommand -> handle(command)
      else -> this
    }

  private fun handle(command: BuySlotsCommand): Raffle {
    validate(command, this)
    return this.copy(
      purchasedTickets = purchasedTickets + command.slots.map {
        Ticket(owner = command.user.name, slotNumber = it)
      }
    )
  }
}
