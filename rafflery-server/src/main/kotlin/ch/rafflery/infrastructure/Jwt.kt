package ch.rafflery.infrastructure

import ch.rafflery.domain.user.User
import com.auth0.jwt.JWT
import com.auth0.jwt.JWTVerifier
import com.auth0.jwt.algorithms.Algorithm
import io.ktor.auth.Principal
import io.ktor.auth.jwt.JWTCredential
import io.ktor.auth.jwt.JWTPrincipal
import java.util.*

object Jwt {

  private const val validityInMs = 36_000_00 * 10 // 10 hours

  private val config = Config.jwtConfig

  private val algorithm = Algorithm.HMAC512(config.secret)

  val verifier: JWTVerifier = JWT
    .require(algorithm)
    .withIssuer(config.issuer)
    .build()

  fun makeToken(user: User): String = JWT.create()
    .withSubject("Authentication")
    .withAudience(config.clientId)
    .withIssuer(config.issuer)
    .withClaim("id", user.name)
    .withExpiresAt(getExpiration())
    .sign(algorithm)

  private fun getExpiration() = Date(System.currentTimeMillis() + validityInMs)

  fun validate(credential: JWTCredential): Principal? =
    credential.payload.audience.contains(config.clientId)
      .let { JWTPrincipal(credential.payload) }
}
