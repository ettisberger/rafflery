package ch.rafflery.domain.raffle

import ch.rafflery.domain.commands.*
import ch.rafflery.domain.prize.PrizeItem

data class Raffle(
  val name: String,
  val item: PrizeItem,
  val slotSize: Int,
  val purchasedTickets: List<Ticket>,
  val createdBy: String,
  val id: String,
  val winningSlot: Int?,
  val winner: String?
) {

  private constructor() :
    this(
      id = "",
      name = "",
      item = PrizeItem(name = "", value = 0),
      slotSize = 0,
      purchasedTickets = emptyList(),
      winningSlot = null,
      winner = null,
      createdBy = ""
    )

  fun handle(command: Command): Raffle =
    when (command) {
      is CreateRaffleCommand -> handle(command)
      is BuySlotsCommand -> handle(command)
      is DrawWinnerCommand -> handle(command)
      else -> this
    }

  private fun handle(command: CreateRaffleCommand): Raffle =
    Raffle(
      id = command.id,
      name = command.name,
      item = PrizeItem(name = command.itemName, value = command.itemValue),
      slotSize = command.itemValue,
      purchasedTickets = emptyList(),
      winningSlot = null,
      winner = null,
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

  private fun handle(command: DrawWinnerCommand): Raffle {
    val winner = purchasedTickets.find { it.slotNumber == command.winningSlot }?.owner
      ?: throw IllegalArgumentException("Nobody has purchased slot number ${command.winningSlot}")

    return this.copy(
      winningSlot = command.winningSlot,
      winner = winner
    )
  }

  companion object {
    operator fun invoke(command: CreateRaffleCommand): Raffle =
      Raffle().handle(command)
  }
}
