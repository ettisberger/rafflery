package ch.rafflery.domain.raffle

import ch.rafflery.domain.prize.PrizeItem

data class Raffle(
    val name: String,
    val item: PrizeItem,
    val slotSize: Int,
    val purchasedTickets: List<Ticket>,
    val createdBy: String,
    val id: String? = null // todo this should not be nullable
)
