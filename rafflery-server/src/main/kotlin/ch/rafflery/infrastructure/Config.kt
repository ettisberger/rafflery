package ch.rafflery.infrastructure

import com.natpryce.konfig.*
import com.natpryce.konfig.ConfigurationProperties.Companion.systemProperties

object Config {
    // app config
    private val app_port = "port" to intType
    private val environment = "environment" to stringType

    // jwt config
    private val jwt_clientId = "jwt.clientId" to stringType
    private val jwt_issuer = "jwt.issuer" to stringType

    // raffler config
    private val raffler_apiUrl = "raffler.apiUrl" to stringType
    private val raffler_apiKey = "raffler.apiKey" to stringType

    private val config = systemProperties() overriding
        EnvironmentVariables().also {
            println("ENVIRONMENT PORT: ${it.getOrNull(Key("port", intType))}")
            println(
                "ENVIRONMENT JWT CLIENT ID: ${it.getOrNull(
                    Key(
                        "JWT_CLIENTID",
                        stringType
                    )
                )}"
            )
            println("ENVIRONMENT JWT ISSUER: ${it.getOrNull(Key("JWT_ISSUER", stringType))}")
        } overriding
        ConfigurationProperties.fromOptionalResource("local.properties") overriding
        ConfigurationProperties.fromResource("default.properties")

    val jwtConfig
        get() = JwtConfig(
            config[jwt_clientId],
            config[jwt_issuer]
        ).also { println("Using JWT config: $it") }

    val appConfig
        get() = AppConfig(
            port = config[app_port],
            environment = config[environment]
        )

    val rafflerConfig
        get() = RafflerConfig(
            config[raffler_apiKey],
            config[raffler_apiUrl]
        )
}

data class AppConfig(
    val port: Int,
    val environment: String
)

data class RafflerConfig(
    val apiKey: String,
    val apiUrl: String
)

data class JwtConfig(
    val clientId: String,
    val issuer: String
)

private infix fun <T> String.to(type: PropertyType<T>): Key<T> = Key(this, type)

private fun ConfigurationProperties.Companion.fromOptionalResource(resourceName: String) =
    if (ClassLoader.getSystemResource(resourceName) != null) fromResource(resourceName)
    else EmptyConfiguration
