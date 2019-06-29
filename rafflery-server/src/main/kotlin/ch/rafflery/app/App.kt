package ch.rafflery.app

import ch.rafflery.controllers.Controller
import io.ktor.application.Application
import io.ktor.application.install
import io.ktor.features.ContentNegotiation
import io.ktor.gson.gson
import io.ktor.http.content.files
import io.ktor.http.content.static
import io.ktor.routing.routing
import io.ktor.server.engine.embeddedServer
import io.ktor.server.netty.Netty
import javax.inject.Inject

const val STATIC_ROUTE = "./rafflery-ui/build/"

class App @Inject constructor(
  private val controllers: Set<@JvmSuppressWildcards Controller>
) {
  fun start() {
    val server = embeddedServer(Netty, port = 8080) {
      init(controllers)
    }

    server.start(wait = true)
  }
}

fun Application.init(controllers: Set<Controller>) {
  install(ContentNegotiation) {
    gson {}
  }

  routing {
    controllers.forEach { it.asRoute(this) }

    static {
      files(STATIC_ROUTE)
    }
  }
}

