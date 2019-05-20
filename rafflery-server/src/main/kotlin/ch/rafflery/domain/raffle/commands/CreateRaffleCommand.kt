package ch.rafflery.domain.raffle.commands

import ch.rafflery.domain.commands.Command
import ch.rafflery.domain.commands.CommandHandler
import ch.rafflery.domain.ports.RaffleRepository
import ch.rafflery.domain.prize.PrizeItem
import ch.rafflery.domain.raffle.Raffle

data class CreateRaffleCommand(
  val name: String,
  val itemName: String,
  val itemValue: Int,
  val slotSize: Int,
  val createdBy: String
) : Command

class CreateRaffleCommandHandler(private val raffleRepository: RaffleRepository) : CommandHandler<CreateRaffleCommand> {

  override fun canHandle(command: Command) = command is CreateRaffleCommand

  override fun handle(command: CreateRaffleCommand) {
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
