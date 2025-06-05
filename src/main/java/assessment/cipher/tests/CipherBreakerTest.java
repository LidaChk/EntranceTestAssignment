package assessment.cipher.tests;

import assessment.cipher.CipherBreaker;

public class CipherBreakerTest {
  static final CipherBreaker cipherBreaker = new CipherBreaker();

  public static void main(String[] args) {

    testBreaking("Khoor, Zruog! Lw'v ph!", "Hello, World! It's me!");
    testBreaking("Sgd Zrrxqhzm bzld cnvm khjd sgd vnke nm sgd enkc,",
        "The Assyrian came down like the wolf on the fold,");
    testBreaking("Фхнжйч, Снх! Пеп ийре?", "Привет, Мир! Как дела?");
    testBreaking("Test", "Test");

    testBreakingFromFile("src/main/java/assessment/cipher/tests/break_inp.txt",
        "src/main/java/assessment/cipher/tests/break_out.txt");
  }

  public static void testBreaking(String input, String expectedOutput) {
    String decryptedText = cipherBreaker.breakCaesar(input);
    if (decryptedText.equals(expectedOutput)) {
      System.out.printf(
          "Breaking Cipher Test Passed: Input \"%s\", Output \"%s\"%n", input, decryptedText);
    } else {
      System.out.printf("Breaking Cipher Test Failed: Input \"%s\",  Expected \"%s\", Got \"%s\"%n",
          input, expectedOutput, decryptedText);
    }
  }

  public static void testBreakingFromFile(String inputFilePath, String outputFilePath) {
    cipherBreaker.processFile(inputFilePath, outputFilePath);
  }
}
