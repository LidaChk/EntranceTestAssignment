# Entrance Test Assignment

## Project Description

This project implements two console applications in Java as part of the Gehtsoft Training Program entrance test. The applications provide Caesar Cipher encryption/decryption and Arithmetic Expression Evaluation functionalities, accessed through a simple console-based user interface.

## How to Compile and Run

* Compile the Java files:
    ```bash
    javac  -encoding UTF-8 -d out/main src/main/java/assessment/Main.java src/main/java/assessment/cipher/*.java src/main/java/assessment/calculator/*.java src/main/java/assessment/ui/*.java src/main/java/assessment/utils/*.java
    ```
*  Run the main application:
    ```bash
    java -cp out/main assessment.Main
    ```
*  Follow the on-screen menu to choose between the available operations.

## Console User Interface (ConsoleUI)

The application provides a simple command-line interface for interacting with the Caesar Cipher and Arithmetic Expression Evaluator functionalities.

Sample Application Flow:

```
Welcome to Gehtsoft Technical Assessment
Please choose an option:
1. Caesar Cipher Encryption
2. Caesar Cipher Decryption
3. Arithmetic Expression Evaluation
4. Exit

Enter your choice:
```

## Part 1: Caesar Cipher Implementation

This part of the project implements the Caesar Cipher algorithm for encrypting and decrypting text.

**Functionality:**

- [x] - Accept text input in Russian and English languages from console
Accept text input in Russian and English languages from a file
- [x] - Accept a shift value (integer)
- [x] - Encrypt plaintext using Caesar cipher algorithm
- [x] - Decrypt ciphertext back to plaintext with receiving a shift value
[ ] **//TODO** Decrypt ciphertext back to plaintext without receiving a shift value

**Requirements:**

- [x] -   Supports both Cyrillic (Russian) and Latin (English) alphabets.
- [x] -   Preserves case (uppercase/lowercase).
- [x] -   Non-alphabetic characters (spaces, punctuation, numbers) remain unchanged.
- [x] -   Handles wrap-around (e.g., with shift 3: 'z' becomes 'c', 'я' becomes 'в').
- [x] -   Supports both positive and negative shift values.

**Input/Output Format:**

*   **Encryption mode:**
    *  ```Caesar Cipher Encryption
          Enter text to encrypt: Hello World
          Enter shift value: 3
          Encrypted text: Khoor Zruog

*   **Decryption mode:**
    *  ```Caesar Cipher Decryption
          Enter text to decrypt: Khoor Zruog
          Enter shift value: 3
          Decrypted text: Hello World

## Part 2: Arithmetic Expression Evaluator

This part of the project parses and evaluates arithmetic expressions using a recursive descent method.

**Functionality:**

- [x] -   Parses and evaluates arithmetic expressions.
- [x] -   Supports basic operations: addition (+), subtraction (-), multiplication (\*), division (/).
- [x] -   Supports parentheses for operation precedence.
- [x] -   Returns the calculated result.

**Requirements:**

- [x] -   Follows standard mathematical order of operations (PEMDAS/BODMAS).
- [x] -   Handles nested parentheses.
- [x] -   Supports decimal numbers.
- [x] -   Handles division by zero appropriately - throws an error.
- [x] -   Supports negative numbers.
- [x] -   Supports unary minus.

**Input/Output Format:**

*   ```
    Arithmetic Expression Evaluation
    Expression: (10 + 5) / 3
    Result: 5.0

*   ```
    Arithmetic Expression Evaluation
    Expression: -5 + 3
    Result: -2.0

*   ```
    Arithmetic Expression Evaluation
    Expression: 2 * (3 + 4) - 1
    Result: 13.0

## How to Run Tests

Test files are located in the `src/main/java/assessment/calculator/` and `src/main/java/assessment/cipher/` directories. These files contain `main` methods and can be executed directly to run the tests.

To run the tests:

*  Navigate to the project's root directory in your terminal.
*  Compile the necessary source files, including the test files:
    ```bash
    javac -encoding UTF-8 -d out/tests src/main/java/assessment/cipher/*.java src/main/java/assessment/calculator/*.java src/main/java/assessment/utils/*.java src/main/java/assessment/calculator/ExpressionEvaluatorTest.java src/main/java/assessment/calculator/TokenAnalyzerTest.java src/main/java/assessment/cipher/CipherTest.java
    ```
*  Run each test file individually:
    ```bash
    java -cp out/tests assessment.calculator.ExpressionEvaluatorTest
    java -cp out/tests  assessment.cipher.CipherTest
    ```

## Approach and Assumptions

The implementation adheres to the requirements specified in the assignment description. The code is organized into separate classes for each functionality (Caesar Cipher and Arithmetic Expression Evaluator) and includes a simple console-based user interface. Proper error handling is implemented for invalid inputs. Test files with `main` methods are provided for key functionalities and can be run directly.

It is assumed that the user has a standard Java development environment set up to compile and run the application and the provided test files.
