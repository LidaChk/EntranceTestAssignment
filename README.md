# Entrance Test Assignment

## Project Description
This project implements the Gehtsoft Entrance Test Assignment with two console applications:
1.  - **Caesar Cipher** - Text encryption/decryption with support for Russian (Cyrillic) and English (Latin) alphabets.
    - **Caesar Cipher Breaker** - Frequency analysis-based decryption without shift value

2. **Arithmetic Expression Evaluator** - Mathematical expression parser with PEMDAS/BODMAS precedence


The implementation follows modular architecture:
- `cipher/` - Caesar cipher logic with language detection
- `calculator/` - Expression parsing and evaluation engine
- `ui/` - Console interface with menu-driven workflow
- `utils/` - Input/output utilities and stream handling

## How to Compile and Run
### Compilation
**Requirements:** Java 11 or higher
```bash
javac -encoding UTF-8 -d bin/main -sourcepath src/main/java $(find src/main/java -type f -name "*.java")
```

### Execution
```bash
java -cp bin/main assessment.Main
```

## Approach and Implementation Details
### Caesar Cipher
The `Cipher` class implements the classic Caesar Cipher , supporting both English (Latin)  and Russian (Cyrillic)  alphabets. It correctly handles:

  - Case preservation
  - Wrap-around logic
  - Positive/negative shifts
  - Non-alphabetic characters are left unchanged

**File Operations**:
  - Supports file input/output with automatic `_out.txt` suffix
  - Uses `FileLineStreamIO` for line-by-line processing (`BufferedReader`, `BufferedWriter`)


#### Cipher Breaking
The `CipherBreaker` class extends `Cipher` and adds functionality to automatically decrypt text without knowing the shift value by using:

  - Language detection  based on character frequency
  - Statistical scoring  of decrypted candidates
  - Frequency analysis  against known language profiles (English/Russian)
  - Vowel percentage checks  to evaluate likelihood of meaningful text
**File Operations**:
  - Uses `FileIO`, that provides convenience methods for reading/writing entire file contents at once. Useful for small files.
**Assumptions**

  - Only Cyrillic (Russian)  and Latin (English)  alphabets are processed; other characters remain unchanged.
  - Input may be a mix of both languages, but output assumes one dominant language.
  - Shift can be any integer (positive, negative, or zero).
  - If input is too short or appears random, automatic decryption might fail.
  - No external dictionaries or NLP tools are used — only statistical models (LanguageProfile) are used.

**File I/O Limitations and Constraints**

  - Input and output file paths must be valid and accessible  from the current working directory.
    Encoding : The code assumes that input files are encoded in the  UTF-8 encoding. There is no explicit encoding specification during file operations.
  - Line endings : Line breaks are preserved during file-based encryption/decryption.
  - Large files : While `FileLineStreamIO` is suitable for large files, there's no progress indicator or cancellation mechanism. `FileIO` is useful for small files
  - Overwriting files : Writing to an existing file will overwrite its contents without warning.
  - Empty files : Attempting to encrypt or decrypt an empty file results in an empty output file.
  - Binary files : This implementation is intended for text files only . Processing binary files will not work as expected.


### Expression Evaluator
The `ExpressionEvaluator` module uses a recursive descent parser  to evaluate arithmetic expressions. It supports:

  - Basic operations: `+`, `-`, `*`, `/`
  - Parentheses for precedence control
  - Decimal numbers and negative values

**Key components:**

  - `TokenAnalyzer` : Tokenizes the input string into numbers, operators, and parentheses.
  - `TokenBuffer` : Provides sequential access to tokens with backtracking.
  - Recursive parsing methods :
      - `expression()` – top-level entry point
      - `plusminus()` – addition and subtraction
      - `multdiv()` – multiplication and division
      - `factor()` – number, parenthesis, unary minus

**Assumptions**

  - Expressions follow standard infix notation.
  - All numbers are valid decimal numbers (with optional negative sign).
  - Division by zero throws an exception.
  - Unary minus is allowed anywhere a factor is expected.
  - Whitespace is ignored during tokenization.
  - Nested parentheses are fully supported.
  - Only basic operators and parentheses are supported; advanced functions like `sqrt`, `sin`, etc., are not included.

## Examples of Usage
### Caesar Cipher Encryption
```bash
Enter text to encrypt: Hello World
Enter shift value: 3
Encrypted text: Khoor Zruog
```

### Caesar Cipher Decryption
```bash
Enter ciphertext: Тулпзу
Enter shift value: 3
Decrypted text: Пример
```

### Cipher Breaking (Automatic)
```bash
Enter encrypted text: Drkxu iye kxigki!
[Warning] The input is too short or contains too few letters.
Frequency analysis may not work correctly and result could be incorrect.
Decrypted text: Thank you anyway!
```

### Arithmetic Evaluation
```bash
Enter expression: (2 + 3) * 4 / 2 - 1
Result: 9.0
```

### File Processing
```bash
Read input from file? (y/n): y
Enter input file path: test.txt
Enter output file path (default test_out.txt):
Processing complete. Check test_out.txt for results.
```

## Sample Menu Flow
```
Welcome to Gehtsoft Technical Assessment
Please choose an option:
1. Caesar Cipher Encryption
2. Caesar Cipher Decryption
3. Caesar Cipher Breaking (Frequency Analysis)
4. Arithmetic Expression Evaluation
5. Exit
```

## Project Structure
```
src/
├── main/
│   └── java/
│       └── assessment/
│           ├── Main.java
│           ├── cipher/
│           │   ├── Cipher.java        # Core encryption/decryption
│           │   ├── CipherBreaker.java # Frequency analysis-based decryption
│           │   ├── LanguageProfile.java # Language-specific frequency data
│           │   └── tests/             # Test files and resources
│           ├── calculator/
│           │   ├── ExpressionEvaluator.java # Main evaluator logic
│           │   ├── Token.java         # Token representation
│           │   ├── TokenAnalyzer.java # Tokenization engine
│           │   ├── TokenBuffer.java   # Token stream management
│           │   └── tests/             # Test files and resources
│           ├── ui/
│           │   └── ConsoleUI.java     # Menu-driven interface
│           └── utils/
│               ├── ScannerUtils.java  # Input validation
│               ├── FileIO.java        # File handling utilities
│               └── FileLineStreamIO.java # File handling utilities
```
