package ch.rafflery.domain.commands

import ch.rafflery.domain.ports.RaffleRepository
import ch.rafflery.domain.prize.PrizeItem
import ch.rafflery.domain.raffle.Raffle
import dagger.Component
import javax.inject.Inject

data class CreateRaffleCommand(
  val name: String,
  val itemName: String,
  val itemValue: Int,
  val slotSize: Int,
  val createdBy: String
)

class CreateRaffleCommandHandler @Inject constructor(
  private val raffleRepository: RaffleRepository
) {

  fun handle(command: CreateRaffleCommand) {
    val raffle = Raffle(
      name = command.name,
      item = PrizeItem(command.itemName, command.itemValue),
      slotSize = command.slotSize,
      purchasedTickets = emptyList(),
      createdBy = "some_user"
    )

    raffleRepository.save(raffle)
  }

}
