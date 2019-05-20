package ch.rafflery

import ch.rafflery.app.App
import ch.rafflery.modules.CommandHandlerModule
import ch.rafflery.modules.RepositoryModule
import dagger.Component
import javax.inject.Singleton

fun main() {
  DaggerRaffleryApp
    .builder()
    .build()
    .app()
    .start()
}

@Singleton
@Component(modules = [RepositoryModule::class, CommandHandlerModule::class])
interface RaffleryApp {
  fun app(): App
}
