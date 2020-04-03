package ch.rafflery.domain.ports

import java.time.Instant

interface IdGenerator {
    fun getId(): String
}

class DummyIdGenerator : IdGenerator {
    override fun getId(): String =
        Instant.now().toEpochMilli().toString()
}
