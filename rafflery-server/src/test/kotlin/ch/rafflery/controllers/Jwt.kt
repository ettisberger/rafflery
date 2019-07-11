package ch.rafflery.controllers

import ch.rafflery.domain.user.User
import ch.rafflery.infrastructure.JwtConfigurator
import ch.rafflery.infrastructure.JwtValidator
import com.auth0.jwt.JWT
import com.auth0.jwt.JWTVerifier
import com.auth0.jwt.algorithms.Algorithm
import io.ktor.auth.jwt.JWTAuthenticationProvider
import java.util.*

object Jwt {

  private const val validityInMs = 36_000_00 * 10 // 10 hours
  private val algorithm = Algorithm.HMAC512("secret")
  private val clientId = "clientId"
  private val issuer = "issuer"

  val verifier: JWTVerifier = JWT
    .require(algorithm)
    .withIssuer(issuer)
    .build()

  fun makeToken(user: User): String = JWT.create()
    .withSubject("Authentication")
    .withAudience(clientId)
    .withIssuer(issuer)
    .withClaim("name", user.name)
    .withExpiresAt(getExpiration())
    .sign(algorithm)

  private fun getExpiration() = Date(System.currentTimeMillis() + validityInMs)
}

object JwtHmac256 : JwtConfigurator {
  override fun configure(configuration: JWTAuthenticationProvider.Configuration) {
    with(configuration) {
      verifier(Jwt.verifier)
      validate {
        JwtValidator.validate(it)
      }
    }
  }
}
