package ch.rafflery.controllers

import io.ktor.application.ApplicationCall
import io.ktor.application.call
import io.ktor.routing.Route
import io.ktor.routing.get
import io.ktor.routing.route

abstract class Controller {

  protected abstract val path: String

  protected abstract suspend fun invoke(call: ApplicationCall)

  fun asRoute(parent: Route) : Route =
    parent.route(path) {
      get {
        invoke(call)
      }
    }
}
