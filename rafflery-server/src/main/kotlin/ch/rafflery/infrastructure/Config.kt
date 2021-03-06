package ch.rafflery.infrastructure

import com.natpryce.konfig.* // ktlint-disable no-wildcard-imports
import com.natpryce.konfig.ConfigurationProperties.Companion.systemProperties

object Config {
    // app config
    private val app_port = "port" to intType
    private val environment = "environment" to stringType

    // jwt config
    private val jwt_clientId = "jwt.clientId" to stringType
    private val jwt_issuer = "jwt.issuer" to stringType

    // raffler config
    private val raffler_api = "raffler.api" to stringType
    private val raffler_api_key = "raffler.api.key" to stringType

    private val config = systemProperties() overriding
            EnvironmentVariables() overriding
            ConfigurationProperties.fromOptionalResource("local.properties") overriding
            ConfigurationProperties.fromResource("default.properties")

    val jwtConfig
        get() = JwtConfig(
            config[jwt_clientId],
            config[jwt_issuer]
        )

    val appConfig
        get() = AppConfig(
            port = config[app_port],
            environment = config[environment]
        )

    val rafflerConfig
        get() = RafflerConfig(
            rafflerApi = config[raffler_api],
            rafflerApiKey = config[raffler_api_key]
        )
}

data class AppConfig(
    val port: Int,
    val environment: String
)

data class JwtConfig(
    val clientId: String,
    val issuer: String
)

data class RafflerConfig(
    val rafflerApi: String,
    val rafflerApiKey: String
)

private infix fun <T> String.to(type: PropertyType<T>): Key<T> = Key(this, type)

private fun ConfigurationProperties.Companion.fromOptionalResource(resourceName: String) =
    if (ClassLoader.getSystemResource(resourceName) != null) fromResource(resourceName)
    else EmptyConfiguration
