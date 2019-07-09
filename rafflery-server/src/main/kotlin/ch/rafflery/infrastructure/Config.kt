package ch.rafflery.infrastructure

import com.natpryce.konfig.*
import com.natpryce.konfig.ConfigurationProperties.Companion.systemProperties

object Config {

  // app config
  private val app_port = "app.port" to intType

  // jwt config
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

  val appConfig
    get() = AppConfig(
        config[app_port]
    )
}

data class AppConfig(
    val port: Int
)

data class JwtConfig(
  val clientId: String,
  val secret: String,
  val issuer: String
)

private infix fun <T> String.to(type: PropertyType<T>): Key<T> =
  Key(this, type)
