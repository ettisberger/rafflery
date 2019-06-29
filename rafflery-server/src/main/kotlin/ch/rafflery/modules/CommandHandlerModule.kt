package ch.rafflery.modules

import ch.rafflery.infrastructure.CommandBus
import ch.rafflery.domain.commands.CommandHandler
import ch.rafflery.domain.ports.RaffleRepository
import ch.rafflery.domain.raffle.commands.CreateRaffleCommandHandler
import dagger.Module
import dagger.Provides
import dagger.multibindings.ElementsIntoSet

@Module
object CommandHandlerModule {

  @JvmStatic
  @Provides
  fun provideCommandBus(commandHandlers: Set<@JvmSuppressWildcards CommandHandler<*>>): CommandBus =
    CommandBus(commandHandlers)

  @JvmStatic
  @JvmSuppressWildcards
  @ElementsIntoSet
  @Provides
  fun provideCommandHandlers(raffleRepository: RaffleRepository) : Set<@JvmSuppressWildcards CommandHandler<*>> =
    setOf(
      CreateRaffleCommandHandler(raffleRepository)
    )
}
