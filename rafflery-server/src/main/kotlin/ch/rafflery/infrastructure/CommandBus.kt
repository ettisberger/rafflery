package ch.rafflery.infrastructure

import ch.rafflery.domain.commands.Command
import ch.rafflery.domain.commands.CommandHandler

interface CommandBus {

    fun <T : Command> submit(command: T)
}

class DefaultCommandBus(
    private val commandHandlers: Set<CommandHandler<*>>
) : CommandBus {

    @Suppress("UNCHECKED_CAST")
    override fun <T : Command> submit(command: T) =
        (handlerFor(command) as CommandHandler<T>).handle(command)

    private fun <T : Command> handlerFor(command: T): CommandHandler<*> =
        commandHandlers.firstOrNull { it.canHandle(command) }
            ?: throw IllegalArgumentException("No CommandHandler found for command $command")
}
