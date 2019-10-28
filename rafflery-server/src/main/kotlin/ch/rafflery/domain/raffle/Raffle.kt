package ch.rafflery.domain.raffle

import ch.rafflery.domain.commands.BuySlotsCommand
import ch.rafflery.domain.commands.Command
import ch.rafflery.domain.commands.CreateRaffleCommand
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

  private constructor() :
    this(
      name = "",
      item = PrizeItem(name = "", value = 0),
      slotSize = 0,
      purchasedTickets = emptyList(),
      id = "",
      createdBy = ""
    )

  fun handle(command: Command): Raffle =
    when (command) {
      is CreateRaffleCommand -> handle(command)
      is BuySlotsCommand -> handle(command)
      else -> this
    }

  private fun handle(command: CreateRaffleCommand): Raffle =
    Raffle(
      name = command.name,
      item = PrizeItem(name = command.itemName, value = command.itemValue),
      slotSize = command.itemValue,
      purchasedTickets = emptyList(),
      id = command.id,
      createdBy = command.createdBy
    )

  private fun handle(command: BuySlotsCommand): Raffle {
    validate(command, this)
    return this.copy(
      purchasedTickets = purchasedTickets + command.slots.map {
        Ticket(owner = command.user.name, slotNumber = it)
      }
    )
  }

  companion object {
    operator fun invoke(command: CreateRaffleCommand): Raffle =
      Raffle().handle(command)
  }
}
