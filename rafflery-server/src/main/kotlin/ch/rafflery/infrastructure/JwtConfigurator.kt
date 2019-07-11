package ch.rafflery.infrastructure

import com.auth0.jwk.JwkProviderBuilder
import io.ktor.auth.Principal
import io.ktor.auth.jwt.JWTAuthenticationProvider
import io.ktor.auth.jwt.JWTCredential
import io.ktor.auth.jwt.JWTPrincipal
import java.util.concurrent.TimeUnit

interface JwtConfigurator {
  fun configure(configuration: JWTAuthenticationProvider.Configuration)
}

object JwtValidator {

  // this should probably do something smarter...
  fun validate(credential: JWTCredential): Principal? =
    credential.payload.audience.contains(Config.jwtConfig.clientId)
      .let { JWTPrincipal(credential.payload) }
}

class JwtRs256 : JwtConfigurator {

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
        JwtValidator.validate(it)
      }
    }

}
