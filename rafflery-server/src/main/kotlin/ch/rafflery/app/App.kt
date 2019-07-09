package ch.rafflery.app

import ch.rafflery.controllers.Controller
import ch.rafflery.infrastructure.Config
import ch.rafflery.infrastructure.Jwt
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
  private val controllers: Set<@JvmSuppressWildcards Controller>
) {
  fun start() {
    val port = Config.appConfig.port ?: (System.getenv("PORT")?.toInt() ?: 8080)

    val server = embeddedServer(Netty, port) {
      init(controllers)
    }

    server.start(wait = true)
  }
}

fun Application.init(controllers: Set<Controller>) {
  install(DefaultHeaders)
  install(CallLogging)
  install(Authentication) {
    jwt {
      verifier(Jwt.verifier)
      validate {
        Jwt.validate(it)
      }
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
