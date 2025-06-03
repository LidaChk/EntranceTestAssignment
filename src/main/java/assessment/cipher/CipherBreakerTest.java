package assessment.cipher;

public class CipherBreakerTest {

  public static void main(String[] args) {
    CipherBreaker cipherBreaker = new CipherBreaker();

    testBreaking("Khoor Zruog тулезх плу", "Hello World привет мир", cipherBreaker);
    testBreaking("Test", "Test", cipherBreaker);

  }

  public static void testBreaking(String input, String expectedOutput, CipherBreaker cipherBreaker) {
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
