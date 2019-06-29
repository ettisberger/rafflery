package ch.rafflery.controllers

import ch.rafflery.app.init
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import io.ktor.server.testing.TestApplicationEngine
import io.ktor.server.testing.TestApplicationResponse
import io.ktor.server.testing.withTestApplication

abstract class ControllerTest {

  val mapper = jacksonObjectMapper()

  protected fun testApp(
    controller: Controller,
    callback: TestApplicationEngine.() -> Unit
  ) {
    withTestApplication({
      init(setOf(controller))
    }) { callback() }
  }

  protected inline fun <reified  T> TestApplicationResponse.getBody(): T {
    return mapper.readValue(this.content!!)
  }
}
