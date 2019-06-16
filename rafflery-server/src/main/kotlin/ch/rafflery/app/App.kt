package ch.rafflery.app

import ch.rafflery.controllers.raffleRoutes
import ch.rafflery.infrastructure.CommandBus
import com.auth0.jwt.JWT
import com.auth0.jwt.JWTVerifier
import com.auth0.jwt.algorithms.Algorithm
import io.ktor.application.call
import io.ktor.application.install
import io.ktor.http.ContentType
import io.ktor.http.content.files
import io.ktor.http.content.static
import io.ktor.response.respondFile
import io.ktor.response.respondText
import io.ktor.routing.get
import io.ktor.routing.routing
import io.ktor.server.engine.embeddedServer
import io.ktor.server.netty.Netty
import io.ktor.auth.*
import io.ktor.auth.jwt.JWTPrincipal
import io.ktor.auth.jwt.jwt
import io.ktor.features.CallLogging
import io.ktor.features.DefaultHeaders
import java.io.File
import javax.inject.Inject

const val STATIC_ROUTE = "./rafflery-ui/build/"

class App @Inject constructor(private val commandBus: CommandBus) {

    fun start() {
        val issuer = "https://jwt-provider-domain/"
        val audience = "jwt-audience"
        val realm = "ktor rafflery"

        val server = embeddedServer(Netty, port = 8080) {

            install(DefaultHeaders)
            install(CallLogging) // log every call
            install(Authentication) {
                val jwtVerifier = makeJwtVerifier(issuer, audience)
                jwt {
                    verifier(jwtVerifier)
                    this.realm = realm
                    validate { credential ->
                        if (credential.payload.audience.contains(audience))
                            JWTPrincipal(credential.payload)
                        else
                            null
                    }
                }
            }

            routing {
                raffleRoutes(commandBus)
                get("/") {
                    call.respondFile(File("$STATIC_ROUTE/index.html"))
                }
                get("/api/hello") {
                    call.respondText("Hello World!", ContentType.Text.Plain)
                }

                authenticate {
                    get("/api/secured") {
                        call.respondText("This is highly secured by jwt tokens.")
                    }
                }

                static {
                    files(STATIC_ROUTE)
                }
            }
        }

        server.start(wait = true)
    }

    private val algorithm = Algorithm.HMAC256("secret")
    private fun makeJwtVerifier(issuer: String, audience: String): JWTVerifier = JWT
            .require(algorithm)
            .withAudience(audience)
            .withIssuer(issuer)
            .build()
}

