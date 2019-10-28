package ch.rafflery.domain.raffle

import ch.rafflery.aRandomRaffle
import ch.rafflery.domain.commands.BuySlotsCommand
import ch.rafflery.domain.commands.CreateRaffleCommand
import ch.rafflery.domain.commands.DrawWinnerCommand
import ch.rafflery.domain.prize.PrizeItem
import ch.rafflery.domain.user.User
import com.natpryce.hamkrest.assertion.assertThat
import com.natpryce.hamkrest.equalTo
import org.junit.jupiter.api.Assertions.assertThrows
import org.junit.jupiter.api.Test

internal class RaffleTest {

  @Test
  fun `can create a raffle`() {
    val command = CreateRaffleCommand(
      id = "1",
      slotSize = 5,
      itemName = "supergeil",
      itemValue = 100,
      name = "suuuuper",
      createdBy = "nick"
    )

    val raffle = Raffle(command)

    val expectedRaffle = Raffle(
      id = command.id,
      name = command.name,
      item = PrizeItem(name = command.itemName, value = command.itemValue),
      slotSize = command.itemValue,
      purchasedTickets = emptyList(),
      winner = null,
      winningSlot = null,
      createdBy = command.createdBy
    )

    assertThat(raffle, equalTo(expectedRaffle))
  }

  @Test
  fun `can buy slots`() {
    val raffle = aRandomRaffle().copy(
      slotSize = 10
    )

    val buySlotsCommand = BuySlotsCommand(
      raffleId = raffle.id,
      slots = listOf(1, 2),
      user = User(name = "notter")
    )

    val updatedRaffle = raffle.handle(buySlotsCommand)

    val expectedTickets = listOf(
      Ticket("notter", 1),
      Ticket("notter", 2)
    )
    assertThat(updatedRaffle.purchasedTickets, equalTo(expectedTickets))
  }

  @Test
  fun `purchasing already purchased slots throws exception`() {
    val raffle = aRandomRaffle().copy(
      purchasedTickets = listOf(
        Ticket(owner = "owner", slotNumber = 1)
      )
    )

    val command = BuySlotsCommand(
      raffleId = raffle.id,
      slots = listOf(1),
      user = User("NDY")
    )

    val exception = assertThrows(IllegalArgumentException::class.java) {
      raffle.handle(command)
    }
    assertThat(exception.message, equalTo("Slots ${command.slots.joinToString()} for raffle ${raffle.id} have already been purchased."))
  }

  @Test
  fun `can draw the winner`() {
    val raffle = aRandomRaffle().copy(
      purchasedTickets = listOf(
        Ticket(owner = "nick", slotNumber = 1),
        Ticket(owner = "andy", slotNumber = 2)
      )
    )

    val command = DrawWinnerCommand(
      raffleId = raffle.id,
      winningSlot = 1
    )

    val updatedRaffle = raffle.handle(command)

    val expectedRaffle = raffle.copy(
      winningSlot = command.winningSlot,
      winner = "nick"
    )

    assertThat(updatedRaffle, equalTo(expectedRaffle))
  }
}
