package ch.rafflery.modules

import ch.rafflery.domain.ports.RaffleRepository
import ch.rafflery.plugins.StubbedRaffleRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
object RepositoryModule {

  @JvmStatic
  @Provides
  @Singleton
  fun provideRaffleRepository() : RaffleRepository =
    StubbedRaffleRepository()

}


