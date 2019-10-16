package ch.rafflery.modules

import ch.rafflery.domain.ports.DummyIdGenerator
import ch.rafflery.domain.ports.IdGenerator
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
object PersistenceModule {

    @JvmStatic
    @Provides
    @Singleton
    fun provideIdGenerator(): IdGenerator =
        DummyIdGenerator()

}
