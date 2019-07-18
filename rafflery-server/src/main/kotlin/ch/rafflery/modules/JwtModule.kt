package ch.rafflery.modules

import ch.rafflery.infrastructure.Config
import ch.rafflery.infrastructure.JwtConfigurator
import ch.rafflery.infrastructure.JwtHmac256
import ch.rafflery.infrastructure.JwtRs256
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
object JwtModule {

    @JvmStatic
    @Provides
    @Singleton
    fun provideJwtConfigurator(): JwtConfigurator =
        if (Config.appConfig.environment == "dev") JwtHmac256
        else JwtRs256
}
