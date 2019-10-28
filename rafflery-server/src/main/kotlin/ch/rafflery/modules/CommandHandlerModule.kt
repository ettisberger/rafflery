package ch.rafflery.modules

import ch.rafflery.domain.commands.BuySlotsCommandHandler
import ch.rafflery.domain.commands.CommandHandler
import ch.rafflery.domain.commands.CreateRaffleCommandHandler
import ch.rafflery.domain.commands.DrawWinnerCommandHandler
import ch.rafflery.domain.ports.RaffleRepository
import ch.rafflery.infrastructure.CommandBus
import ch.rafflery.infrastructure.DefaultCommandBus
import dagger.Module
import dagger.Provides
import dagger.multibindings.ElementsIntoSet

@Module
object CommandHandlerModule {

    @JvmStatic
    @Provides
    fun provideCommandBus(commandHandlers: Set<@JvmSuppressWildcards CommandHandler<*>>): CommandBus =
        DefaultCommandBus(commandHandlers)

    @JvmStatic
    @JvmSuppressWildcards
    @ElementsIntoSet
    @Provides
    fun provideCommandHandlers(raffleRepository: RaffleRepository): Set<@JvmSuppressWildcards CommandHandler<*>> =
        setOf(
            CreateRaffleCommandHandler(raffleRepository),
            BuySlotsCommandHandler(raffleRepository),
            DrawWinnerCommandHandler(raffleRepository)
        )
}
