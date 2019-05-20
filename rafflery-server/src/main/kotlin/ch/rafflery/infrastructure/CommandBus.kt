package ch.rafflery.infrastructure

import ch.rafflery.domain.commands.Command
import ch.rafflery.domain.commands.CommandHandler

class CommandBus(
  private val commandHandlers: Set<CommandHandler<*>>
) {

  // why is this being ignored ?
  @SuppressWarnings("UNCHECKED_CAST")
  fun <T : Command> submit(command: T) =
    (handlerFor(command) as CommandHandler<T>).handle(command)

  private fun <T: Command> handlerFor(command: T): CommandHandler<*> =
    commandHandlers.firstOrNull { it.canHandle(command) }
      ?: throw IllegalArgumentException("No CommandHandler found for command $command")

}
