package ch.rafflery.controllers

import io.ktor.application.ApplicationCall
import io.ktor.application.call
import io.ktor.auth.Principal
import io.ktor.auth.authenticate
import io.ktor.auth.authentication
import io.ktor.auth.jwt.JWTPrincipal
import io.ktor.routing.Route
import io.ktor.routing.get
import io.ktor.routing.post
import io.ktor.routing.route

abstract class SecureController: Controller() {

  override fun asRoute(parent: Route): Route =
    parent.route(path) {
      authenticate {
        when (method) {
          "GET" -> get {
            protecc(call)
            invoke(call)
          }
          "POST" -> post {
            protecc(call)
            invoke(call)
          }
        }
      }
    }

  private fun protecc(call: ApplicationCall) {
    val user = call.user
    println("User $user is authenticated")
  }
}

data class User(val name: String) : Principal

val ApplicationCall.user: User
  get(): User {
    val principal: JWTPrincipal? = authentication.principal()
    val name = principal?.payload?.getClaim("name")?.asString()
    if (name != null) {
      return User(name)
    }
    throw IllegalArgumentException("User not authenticated")
  }
