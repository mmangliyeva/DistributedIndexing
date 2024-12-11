import java.util.*
import java.io.File

class Compiler {

    private val scopeStack: Deque<MutableMap<String, Int?>> = ArrayDeque()

    init {
        scopeStack.push(mutableMapOf())
    }

    fun executeProgram(program: String) {
        val lines = program.trim().lines().map { it.trim() }
        for ((lineNumber, line) in lines.withIndex()) {
            try {
                executeCommand(line)
            } catch (e: Exception) {
                println("Error at line $lineNumber: ${e.message}")
            }
        }

        if (scopeStack.size > 1) {
            println("Unclosed scopes detected. Ensure all 'scope {' have matching '}'.")
        }
    }

    private fun executeCommand(command: String) {
        when {
            command.startsWith("print ") -> handlePrint(command)
            command.startsWith("scope {") -> scopeStack.push(mutableMapOf())
            command == "}" -> {
                if (scopeStack.size > 1) {
                    scopeStack.pop()
                } else {
                    throw IllegalArgumentException("Unmatched closing brace }")
                }
            }
            "=" in command -> handleAssignment(command)
            command.isBlank() -> return
            else -> throw IllegalArgumentException("Unknown command: $command")
        }
    }

    private fun handleAssignment(command: String) {
        val parts = command.split("=").map { it.trim() }
        if (parts.size != 2 || parts[0].isEmpty()) {
            throw IllegalArgumentException("Invalid assignment: $command")
        }

        val variable = parts[0]
        if (!variable.matches(Regex("[a-zA-Z_][a-zA-Z0-9_]*"))) {
            throw IllegalArgumentException("Invalid variable name: $variable")
        }

        val valueExpression = parts[1]
        val currentScope = scopeStack.peek()
        if (valueExpression.all { it.isDigit() }) {

            currentScope[variable] = valueExpression.toInt()
        } else {

            val resolvedValue = resolveVariable(valueExpression)
                ?: throw IllegalArgumentException("Undefined variable: $valueExpression")
            currentScope[variable] = resolvedValue
        }
    }

    private fun handlePrint(command: String) {
        val variable = command.removePrefix("print ").trim()
        if (!variable.matches(Regex("[a-zA-Z_][a-zA-Z0-9_]*"))) {
            throw IllegalArgumentException("Invalid variable name in print: $variable")
        }
        val value = resolveVariable(variable)
        println(value ?: "null")
    }

    private fun resolveVariable(variable: String): Int? {
        for (scope in scopeStack) {
            if (variable in scope) {
                return scope[variable]
            }
        }
        return null
    }
}

fun main(args: Array<String>) {
    val inputFilePath = args[0]

    try {
        val program = File(inputFilePath).readText()
        val compiler = Compiler()
        compiler.executeProgram(program)
    } catch (e: Exception) {
        println("Error: ${e.message}")
    }
}