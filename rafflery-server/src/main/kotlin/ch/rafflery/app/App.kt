package ch.rafflery.app

import ch.rafflery.controllers.raffleRoutes
import ch.rafflery.infrastructure.CommandBus
import com.auth0.jwk.JwkProviderBuilder
import com.auth0.jwt.interfaces.Claim
import io.ktor.application.call
import io.ktor.application.install
import io.ktor.auth.Authentication
import io.ktor.auth.authenticate
import io.ktor.auth.authentication
import io.ktor.auth.jwt.JWTPrincipal
import io.ktor.auth.jwt.jwt
import io.ktor.features.CallLogging
import io.ktor.features.DefaultHeaders
import io.ktor.http.ContentType
import io.ktor.http.content.files
import io.ktor.http.content.static
import io.ktor.response.respondFile
import io.ktor.response.respondText
import io.ktor.routing.get
import io.ktor.routing.routing
import io.ktor.server.engine.embeddedServer
import io.ktor.server.netty.Netty
import java.io.File
import java.util.concurrent.TimeUnit
import javax.inject.Inject


const val STATIC_ROUTE = "./rafflery-ui/build/" // TODO application.conf

class App @Inject constructor(private val commandBus: CommandBus) {

    fun start() {
        val issuer = "https://rafflery.eu.auth0.com/" // TODO application.conf DOMAIN
        val audience = "wvDUhCjqPf0cDiAzPx62tLifTd4lI84z" // // TODO application.conf CLIENT ID
        val jwkProvider = JwkProviderBuilder(issuer) // uses jwt keyset
                .cached(10, 24, TimeUnit.HOURS)
                .rateLimited(10, 1, TimeUnit.MINUTES)
                .build()

        val server = embeddedServer(Netty, port = 8080) {
            install(DefaultHeaders)
            install(CallLogging) // log every rest call
            install(Authentication) {
                jwt {
                    verifier(jwkProvider, issuer)
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

                get("/*") {
                    call.respondFile(File("$STATIC_ROUTE/index.html"))
                }

                get("/api/hello") {
                    call.respondText("Hello World!", ContentType.Text.Plain)
                }

                authenticate {
                    get("/api/secured") {
                        call.respondText("This is highly secured by jwt tokens.")
                        val principal: JWTPrincipal? = call.authentication.principal()

                        if (principal != null) {
                            val name: Claim = principal.payload.getClaim("name")

                            System.out.println("Logged in with username $name.asString()")
                        }

                    }
                }

                static {
                    files(STATIC_ROUTE)
                }
            }
        }

        server.start(wait = true)
    }
}

