package ch.rafflery.modules

import ch.rafflery.controllers.* // ktlint-disable no-wildcard-imports
import ch.rafflery.domain.ports.RaffleRepository
import ch.rafflery.infrastructure.CommandBus
import dagger.Module
import dagger.Provides

@Module
object ControllerModule {

    @JvmStatic
    @Provides
    fun provideControllers(repository: RaffleRepository, commandBus: CommandBus): Set<@JvmSuppressWildcards Controller> =
        setOf(
            GetRafflesController(repository),
            BuySlotsController(commandBus),
            IndexController(),
            RefreshController(),
            GetUiConfigController()
        )
}
