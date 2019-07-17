package ch.rafflery.modules

import ch.rafflery.controllers.*
import ch.rafflery.domain.ports.RaffleRepository
import dagger.Module
import dagger.Provides

@Module
object ControllerModule {

  @JvmStatic
  @Provides
  fun provideControllers(repository: RaffleRepository): Set<@JvmSuppressWildcards Controller> =
    setOf(
      GetRafflesController(repository),
      IndexController(),
      RefreshController(),
      GetUiConfigController()
    )
}
