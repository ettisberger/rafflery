package ch.rafflery.controllers

import ch.rafflery.app.init
import ch.rafflery.domain.commands.Command
import ch.rafflery.domain.user.User
import ch.rafflery.infrastructure.CommandBus
import ch.rafflery.infrastructure.JwtHmac256
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import io.ktor.http.ContentType
import io.ktor.http.HttpHeaders
import io.ktor.http.HttpMethod
import io.ktor.http.HttpStatusCode
import io.ktor.server.testing.* // ktlint-disable no-wildcard-imports
import kotlin.test.assertEquals

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
            init(setOf(controller), JwtHmac256)
        }) { callback() }
    }

    protected inline fun <reified T> TestApplicationResponse.getBody(): T {
        return mapper.readValue(this.content!!)
    }

    protected inline infix fun <reified T> TestApplicationCall.shouldHaveBody(body: T) {
        assertEquals(body, response.getBody())
    }

    protected infix fun TestApplicationCall.shouldHaveStatus(code: HttpStatusCode) =
        assertEquals(code, response.status())

    protected fun TestApplicationEngine.getSecure(path: String): TestApplicationCall {
        return handleRequest {
            uri = path
            method = HttpMethod.Get
            addHeader("Authorization", "Bearer ${getToken()}")
        }
    }

    protected fun TestApplicationEngine.get(path: String): TestApplicationCall {
        return handleRequest {
            uri = path
            method = HttpMethod.Get
        }
    }

    protected fun TestApplicationEngine.post(path: String, request: Any): TestApplicationCall {
        return handleRequest(HttpMethod.Post, path) {
            addHeader(HttpHeaders.ContentType, ContentType.Application.Json.toString())
            setBody(mapper.writeValueAsString(request))
        }
    }

    protected fun TestApplicationEngine.postSecure(
        path: String,
        request: Any
    ): TestApplicationCall {
        return handleRequest(HttpMethod.Post, path) {
            addHeader(HttpHeaders.ContentType, ContentType.Application.Json.toString())
            setBody(mapper.writeValueAsString(request))
            addHeader("Authorization", "Bearer ${getToken()}")
        }
    }

    private fun getToken() = JwtHmac256.makeToken(User("testUser"))
}
