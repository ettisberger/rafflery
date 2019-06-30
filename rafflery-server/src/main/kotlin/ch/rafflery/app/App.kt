package ch.rafflery.app

import ch.rafflery.controllers.Controller
import com.auth0.jwk.JwkProviderBuilder
import io.ktor.application.Application
import io.ktor.application.install
import io.ktor.auth.Authentication
import io.ktor.auth.jwt.JWTPrincipal
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
import java.util.concurrent.TimeUnit
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
  val issuer = "https://rafflery.eu.auth0.com/" // TODO application.conf DOMAIN
  val audience = "wvDUhCjqPf0cDiAzPx62tLifTd4lI84z" // // TODO application.conf CLIENT ID
  val jwkProvider = JwkProviderBuilder(issuer) // uses jwt keyset
    .cached(10, 24, TimeUnit.HOURS)
    .rateLimited(10, 1, TimeUnit.MINUTES)
    .build()

  install(DefaultHeaders)
  install(CallLogging) // log every rest call
  install(Authentication) {
    jwt {
      verifier(jwkProvider, issuer)
      validate { credential ->
        if (credential.payload.audience.contains(audience))
          JWTPrincipal(credential.payload)
        else
          null
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

