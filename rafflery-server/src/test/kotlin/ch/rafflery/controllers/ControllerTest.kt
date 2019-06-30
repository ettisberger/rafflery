package ch.rafflery.controllers

import ch.rafflery.app.init
import ch.rafflery.domain.commands.Command
import ch.rafflery.infrastructure.CommandBus
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import io.ktor.http.ContentType
import io.ktor.http.HttpHeaders
import io.ktor.http.HttpMethod
import io.ktor.server.testing.*

abstract class ControllerTest {

  val mapper = jacksonObjectMapper()

  object FakeCommandBus : CommandBus {

    val commands = mutableListOf<Command>()

    override fun <T : Command> submit(command: T) {
      commands.add(command)
    }
  }

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

  protected fun TestApplicationEngine.post(path: String, request: Any, then: () -> Unit) {
    handleRequest(HttpMethod.Post, path) {
      addHeader(HttpHeaders.ContentType, ContentType.Application.Json.toString())
      setBody(mapper.writeValueAsString(request))
    }.apply {
      then()
    }
  }
}
