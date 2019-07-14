package ch.rafflery.controllers

import ch.rafflery.domain.user.User
import ch.rafflery.infrastructure.JwtConfigurator
import com.auth0.jwt.JWT
import com.auth0.jwt.JWTVerifier
import com.auth0.jwt.algorithms.Algorithm
import io.ktor.auth.Principal
import io.ktor.auth.jwt.JWTAuthenticationProvider
import io.ktor.auth.jwt.JWTCredential
import io.ktor.auth.jwt.JWTPrincipal

object MockJwt {

  private val algorithm = Algorithm.HMAC512("secret")
  const val clientId = "clientId"
  private const val issuer = "issuer"

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
      verifier(MockJwt.verifier)
      validate {
        validate(it)
      }
    }
  }

  private fun validate(credential: JWTCredential): Principal? =
    credential.payload.audience.contains(MockJwt.clientId)
      .let { JWTPrincipal(credential.payload) }
}
