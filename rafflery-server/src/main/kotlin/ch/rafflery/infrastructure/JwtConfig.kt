package ch.rafflery.infrastructure

import ch.rafflery.domain.user.User
import com.auth0.jwt.JWT
import com.auth0.jwt.JWTVerifier
import com.auth0.jwt.algorithms.Algorithm
import io.ktor.auth.Principal
import io.ktor.auth.jwt.JWTCredential
import io.ktor.auth.jwt.JWTPrincipal
import java.util.*

object JwtConfig {

  const val clientId = "wvDUhCjqPf0cDiAzPx62tLifTd4lI84z"
  const val secret = "5fsdJnKl7OlcVCFP8lcEnJmCyF7EMTmdiya8OKbu1ujvbu_UHy0E2z3Kq1TP9QN1"
  const val issuer = "https://rafflery.eu.auth0.com/"
  const val validityInMs = 36_000_00 * 10 // 10 hours
  private val algorithm = Algorithm.HMAC512(secret)

  val verifier: JWTVerifier = JWT
    .require(algorithm)
    .withIssuer(issuer)
    .build()

  fun makeToken(user: User): String = JWT.create()
    .withSubject("Authentication")
    .withAudience(clientId)
    .withIssuer(issuer)
    .withClaim("id", user.name)
    .withExpiresAt(getExpiration())
    .sign(algorithm)

  private fun getExpiration() = Date(System.currentTimeMillis() + validityInMs)

  fun validate(credential: JWTCredential): Principal? =
    credential.payload.audience.contains(clientId)
      .let { JWTPrincipal(credential.payload) }
}
