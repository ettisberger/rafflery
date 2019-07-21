package ch.rafflery.domain.raffle

data class RaffleResult(
    val id: String?,
    val signature: String?,
    val winnerSlot: Int
)
