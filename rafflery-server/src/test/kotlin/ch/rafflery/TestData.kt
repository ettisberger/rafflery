package ch.rafflery

import ch.rafflery.domain.commands.CreateRaffleCommand
import ch.rafflery.domain.prize.PrizeItem
import ch.rafflery.domain.raffle.Raffle
import org.apache.commons.lang3.RandomStringUtils.random

fun aRandomString(): String = random(10)
fun aRandomInt(): Int = (Math.random() * 100_000).toInt()

fun aRandomRaffle() = Raffle(
  name = aRandomString(),
  item = PrizeItem(aRandomString(), aRandomInt()),
  slotSize = aRandomInt(),
  purchasedTickets = emptyList(),
  createdBy = aRandomString(),
  id = aRandomString(),
  winner = null,
  winningSlot = null
)

fun aRandomCreateRaffleCommand() = CreateRaffleCommand(
  id = aRandomString(),
  name = aRandomString(),
  itemName = aRandomString(),
  itemValue = aRandomInt(),
  slotSize = aRandomInt(),
  createdBy = aRandomString()
)

