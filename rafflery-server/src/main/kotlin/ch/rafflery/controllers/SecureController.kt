package ch.rafflery.controllers

import ch.rafflery.domain.user.User
import io.ktor.application.ApplicationCall
import io.ktor.application.call
import io.ktor.auth.authenticate
import io.ktor.auth.authentication
import io.ktor.auth.jwt.JWTPrincipal
import io.ktor.routing.Route
import io.ktor.routing.get
import io.ktor.routing.post
import io.ktor.routing.route

abstract class SecureController : Controller() {

    override fun asRoute(parent: Route): Route =
        parent.route(path) {
            authenticate {
                when (method) {
                    "GET" -> get {
                        verify(call)
                        invoke(call)
                    }
                    "POST" -> post {
                        verify(call)
                        invoke(call)
                    }
                }
            }
        }

    private fun verify(call: ApplicationCall) {
        val user = call.user
        println("User $user is authenticated")
    }
}

val ApplicationCall.user: User
    get(): User {
        val principal: JWTPrincipal? = authentication.principal()
        val name = principal?.payload?.getClaim("name")?.asString()
        if (name != null) {
            return User(name)
        }
        throw IllegalArgumentException("User not authenticated")
    }
