# Programming Language Compiler

This project implements a simple compiler for a small programming language that supports variable assignment, scope-based variable handling, and printing variable values. It processes commands from a string input file, handles variable assignments within different scopes, and ensures correct handling of nested scopes.

## Features

- **Variable Assignment**:
    - Assign integer values to variables.
    - Assign a variable's value to another variable.
- **Scopes**:
    - Variables can be declared inside nested `scope {}` blocks.
    - Variables declared within a scope are local to that scope and are removed when the scope is exited.
- **Printing Variables**:
    - Print the value of a variable (or "null" if the variable is not defined in the current scope).
- **Error Handling**:
    - Provides detailed error messages for invalid syntax, undefined variables, and unmatched scopes.

## Requirements

- Kotlin 2.0.21 or later
- JVM (Java 8 or higher)

## Setup

1. Clone the repository:

   ```bash
   git clone hhttps://github.com/mmangliyeva/DistributedIndexing.git
   cd DistributedIndexing
   ```
2. Build the project using Gradle:

```bash
./gradlew build
```

3. Run the program:
```bash
./gradlew run  --args="./src/main/resources/program.txt"
```
