package ch.rafflery.app

import ch.rafflery.controllers.Controller
import ch.rafflery.infrastructure.Config
import ch.rafflery.infrastructure.JwtConfigurator
import io.ktor.application.Application
import io.ktor.application.install
import io.ktor.auth.Authentication
import io.ktor.auth.jwt.jwt
import io.ktor.features.CallLogging
import io.ktor.features.ContentNegotiation
import io.ktor.features.DefaultHeaders
import io.ktor.gson.gson
import io.ktor.http.content.files
import io.ktor.http.content.static
import io.ktor.routing.routing
import io.ktor.server.engine.embeddedServer
import io.ktor.server.netty.Netty
import javax.inject.Inject

const val STATIC_ROUTE = "./rafflery-ui/build/"

class App @Inject constructor(
  private val controllers: Set<@JvmSuppressWildcards Controller>,
  private val jwtConfigurator: JwtConfigurator
) {
  fun start() {
    val server = embeddedServer(Netty, Config.appConfig.port) {
      init(controllers, jwtConfigurator)
    }

    server.start(wait = true)
  }
}

fun Application.init(
  controllers: Set<Controller>,
  jwtConfigurator: JwtConfigurator
) {
  install(DefaultHeaders)
  install(CallLogging)
  install(Authentication) {
    jwt {
      jwtConfigurator.configure(this)
    }
  }
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

