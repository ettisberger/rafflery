package ch.rafflery.infrastructure

import com.natpryce.konfig.*
import com.natpryce.konfig.ConfigurationProperties.Companion.systemProperties

object Config {

  private val jwt_clientId = "jwt.clientId" to stringType
  private val jwt_secret = "jwt.secret" to stringType
  private val jwt_issuer = "jwt.issuer" to stringType

  private val config = systemProperties() overriding
    EnvironmentVariables() overriding
    ConfigurationProperties.fromResource("local.properties")

  val jwtConfig
    get() = JwtConfig(
      config[jwt_clientId],
      config[jwt_secret],
      config[jwt_issuer]
    ).also { println("Using JWT config: $it") }
}

data class JwtConfig(
  val clientId: String,
  val secret: String,
  val issuer: String
)

private infix fun <T> String.to(type: PropertyType<T>): Key<T> =
  Key(this, type)
