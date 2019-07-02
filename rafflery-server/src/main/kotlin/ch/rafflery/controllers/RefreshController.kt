package ch.rafflery.controllers

import ch.rafflery.app.STATIC_ROUTE
import io.ktor.application.ApplicationCall
import io.ktor.response.respondFile
import java.io.File

/**
 * We need to route * to the index
 * otherwise refreshs on main route dont work
 * isnt there an easier way this seems stupid as fuck
 */
class RefreshController : Controller() {

  override val method = "GET"
  override val path = "/*"

  override suspend fun invoke(call: ApplicationCall) {
    call.respondFile(File("$STATIC_ROUTE/index.html"))
  }
}
