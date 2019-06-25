package ch.rafflery.controllers

import ch.rafflery.app.STATIC_ROUTE
import com.auth0.jwt.interfaces.Claim
import io.ktor.application.call
import io.ktor.auth.authenticate
import io.ktor.auth.authentication
import io.ktor.auth.jwt.JWTPrincipal
import io.ktor.http.ContentType
import io.ktor.http.content.files
import io.ktor.http.content.static
import io.ktor.response.respondFile
import io.ktor.response.respondText
import io.ktor.routing.Route
import io.ktor.routing.get
import java.io.File

fun Route.mainRoutes() {
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
