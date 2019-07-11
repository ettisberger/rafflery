package ch.rafflery.modules

import ch.rafflery.infrastructure.JwtConfigurator
import ch.rafflery.infrastructure.JwtRs256
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
object JwtModule {

  @JvmStatic
  @Provides
  @Singleton
  fun provideJwtConfigurator() : JwtConfigurator =
    JwtRs256()
}


