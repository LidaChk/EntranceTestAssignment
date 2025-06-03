package assessment.cipher;

public class CipherTest {

  public static void main(String[] args) {
    Cipher cipher = new Cipher();

    testEncryption("Hello World", 3, "Khoor Zruog", cipher);
    testEncryption("Привет Мир", 5, "Хумёзй Рну", cipher);
    testEncryption("Hello World 123!", 3, "Khoor Zruog 123!", cipher);
    testEncryption("aBcDeFg", 1, "bCdEfGh", cipher);
    testEncryption("Test", 0, "Test", cipher);
    testEncryption("Test", 26, "Test", cipher);
    testEncryption("Тест", 34, "Тест", cipher);
    testEncryption("Hello World привет мир 123!", 3, "Khoor Zruog тулезх плу 123 123!", cipher);

    testDecryption("Khoor Zruog", 3, "Hello World", cipher);
    testDecryption("Khoor Zruog 123!", 3, "Hello World 123!", cipher);
    testDecryption("bCdEfGh", 1, "aBcDeFg", cipher);
    testDecryption("Test", 0, "Test", cipher);
    testDecryption("Test", 26, "Test", cipher);
    testDecryption("Тест", 33, "Тест", cipher);
  }

  public static void testEncryption(String input, int shift, String expectedOutput, Cipher cipher) {
    String encryptedText = cipher.encrypt(input, shift);
    if (encryptedText.equals(expectedOutput)) {
      System.out.printf(
          "Encryption Test Passed: Input \"%s\", Shift %d, Output \"%s\"%n", input, shift, encryptedText);
    } else {
      System.out.printf("Encryption Test Failed: Input \"%s\", Shift %d, Expected \"%s\", Got \"%s\"%n",
          input, shift, expectedOutput, encryptedText);
    }
  }

  public static void testDecryption(String input, int shift, String expectedOutput, Cipher cipher) {
    String decryptedText = cipher.decrypt(input, shift);
    if (decryptedText.equals(expectedOutput)) {
      System.out.printf(
          "Decryption Test Passed: Input \"%s\", Shift %d, Output \"%s\"%n", input, shift, decryptedText);
    } else {
      System.out.printf("Decryption Test Failed: Input \"%s\", Shift %d, Expected \"%s\", Got \"%s\"%n",
          input, shift, expectedOutput, decryptedText);
    }
  }
}
