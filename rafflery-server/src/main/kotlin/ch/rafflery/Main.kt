package ch.rafflery

import ch.rafflery.app.App
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

@Component(
  modules = [
    RepositoryModule::class
  ]
)
@Singleton
interface RaffleryApp {
  fun app(): App
}
