package ch.rafflery.controllers

import ch.rafflery.app.STATIC_ROUTE
import io.ktor.application.ApplicationCall
import io.ktor.response.respondFile
import java.io.File

class IndexController : Controller() {
  override val path = "/"

  override suspend fun invoke(call: ApplicationCall) {
    call.respondFile(File("$STATIC_ROUTE/index.html"))
  }
}
