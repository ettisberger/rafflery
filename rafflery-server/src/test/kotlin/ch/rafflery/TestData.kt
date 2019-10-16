package ch.rafflery

import ch.rafflery.domain.commands.CreateRaffleCommand
import ch.rafflery.domain.prize.PrizeItem
import ch.rafflery.domain.raffle.Raffle
import ch.rafflery.domain.raffle.Ticket

fun aRandomRaffle() = Raffle(
  name = "Rolex GMT Master II",
  item = PrizeItem("Rolex GMT Master II", 10000),
  slotSize = 100,
  purchasedTickets = mutableListOf(
    Ticket("Andy", 1),
    Ticket("Nicholas", 2)
  ),
  createdBy = "ninula",
  id = "1"
)

fun aRandomCreateRaffleCommand() = CreateRaffleCommand(
  id = "1",
  name = "name",
  itemName = "itemName",
  itemValue = 10,
  slotSize = 10,
  createdBy = "gomph"
)

