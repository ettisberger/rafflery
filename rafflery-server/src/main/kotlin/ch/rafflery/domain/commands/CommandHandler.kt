package ch.rafflery.domain.commands

interface Command

interface CommandHandler<in T : Command> {

  fun canHandle(command: Command): Boolean

  fun handle(command: T)

}
