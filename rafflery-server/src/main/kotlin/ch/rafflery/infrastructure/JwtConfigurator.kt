package ch.rafflery.infrastructure

import ch.rafflery.domain.user.User
import com.auth0.jwk.JwkProviderBuilder
import com.auth0.jwt.JWT
import com.auth0.jwt.JWTVerifier
import com.auth0.jwt.algorithms.Algorithm
import io.ktor.auth.Principal
import io.ktor.auth.jwt.JWTAuthenticationProvider
import io.ktor.auth.jwt.JWTCredential
import io.ktor.auth.jwt.JWTPrincipal
import java.util.concurrent.TimeUnit

interface JwtConfigurator {
    fun configure(configuration: JWTAuthenticationProvider.Configuration)
}

object JwtRs256 : JwtConfigurator {

    private val config = Config.jwtConfig

    private val jwkProvider =
        JwkProviderBuilder(config.issuer) // uses jwt keyset
            .cached(10, 24, TimeUnit.HOURS)
            .rateLimited(10, 1, TimeUnit.MINUTES)
            .build()

    override fun configure(configuration: JWTAuthenticationProvider.Configuration) =
        with(configuration) {
            verifier(jwkProvider, config.issuer)
            validate {
                validate(it)
            }
        }

    private fun validate(credential: JWTCredential): Principal? =
        credential.payload.audience.contains(Config.jwtConfig.clientId)
            .let { JWTPrincipal(credential.payload) }
}

object JwtHmac256 : JwtConfigurator {

    private val algorithm = Algorithm.HMAC256("secret")
    private const val clientId = "clientId"
    private const val issuer = "issuer"

    private val verifier: JWTVerifier = JWT
        .require(algorithm)
        .withIssuer(issuer)
        .build()

    fun makeToken(user: User): String = JWT.create()
        .withSubject("Authentication")
        .withAudience(clientId)
        .withIssuer(issuer)
        .withClaim("name", user.name)
        .sign(algorithm)


    override fun configure(configuration: JWTAuthenticationProvider.Configuration) {
        with(configuration) {
            verifier(verifier)
            validate {
                validate(it)
            }
        }
    }

    private fun validate(credential: JWTCredential): Principal? =
        credential.payload.audience.contains(clientId)
            .let { JWTPrincipal(credential.payload) }


}
