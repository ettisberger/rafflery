package ch.rafflery.assertions

import kotlin.test.assertEquals

infix fun Any.shouldBe(expected: Any) {
    assertEquals(expected, this)
}
