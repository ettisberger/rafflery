package ch.rafflery.controllers

import ch.rafflery.infrastructure.Config
import io.ktor.application.ApplicationCall
import io.ktor.response.respond

class GetUiConfigController : Controller() {

  override val method = "GET"
  override val path = "/api/ui-config"

  override suspend fun invoke(call: ApplicationCall) =
    call.respond(
      UiConfig(
        environment = Config.appConfig.environment
      )
    )
}

data class UiConfig(
  val environment: String
)
