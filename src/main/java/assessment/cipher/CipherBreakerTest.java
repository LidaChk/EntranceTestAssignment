package assessment.cipher;

public class CipherBreakerTest {
  static final CipherBreaker cipherBreaker = new CipherBreaker();

  public static void main(String[] args) {

    testBreaking("Khoor Zruog", "Hello World");
    testBreaking("Хумёзй Рну", "Привет Мир");
    // testBreaking("Test", "Test");
    // testBreaking("Khoor Zruog тулезх плу", "Hello World привет мир");
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
}
