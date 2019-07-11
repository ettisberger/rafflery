package ch.rafflery.controllers

import ch.rafflery.domain.user.User
import ch.rafflery.infrastructure.JwtConfigurator
import ch.rafflery.infrastructure.JwtValidator
import com.auth0.jwt.JWT
import com.auth0.jwt.JWTVerifier
import com.auth0.jwt.algorithms.Algorithm
import io.ktor.auth.jwt.JWTAuthenticationProvider

object Jwt {

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
    .sign(algorithm)

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
