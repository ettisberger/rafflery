package ch.rafflery.domain.raffle

import ch.rafflery.domain.commands.BuySlotsCommand
import ch.rafflery.domain.commands.Command
import ch.rafflery.domain.prize.PrizeItem
import java.time.Instant

val EMPTY_ID = Instant.now().toString()

data class Raffle(
  val name: String,
  val item: PrizeItem,
  val slotSize: Int,
  val purchasedTickets: List<Ticket>,
  val createdBy: String,
  val id: String = EMPTY_ID
) {

  fun handle(command: Command): Raffle =
    when (command) {
      is BuySlotsCommand -> handle(command)
      else -> this
    }

  fun handle(buySlotsCommand: BuySlotsCommand): Raffle {
    return this
  }

}
