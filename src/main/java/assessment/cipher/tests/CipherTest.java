
package assessment.cipher.tests;

import assessment.cipher.Cipher;

public class CipherTest {
  static final Cipher cipher = new Cipher();

  public static void main(String[] args) {

    testEncryption("Hello World", 3, "Khoor Zruog");
    testEncryption("Привет Мир", 5, "Фхнжйч Снх");
    testEncryption("Hello World 123!", 3, "Khoor Zruog 123!");
    testEncryption("aBcDeFg", 1, "bCdEfGh");
    testEncryption("bCdEfGh", -1, "aBcDeFg");
    testEncryption("Test", 0, "Test");
    testEncryption("Test", 26, "Test");
    testEncryption("Тест", 33, "Тест");
    testEncryption("Hello World привет мир 123!", 3, "Khoor Zruog тулезх плу 123!");

    testDecryption("Khoor Zruog", 3, "Hello World");
    testDecryption("Khoor Zruog 123!", 3, "Hello World 123!");
    testDecryption("bCdEfGh", 1, "aBcDeFg");
    testDecryption("Test", 0, "Test");
    testDecryption("Test", 26, "Test");
    testDecryption("Тест", 33, "Тест");
  }

  public static void testEncryption(String input, int shift, String expectedOutput) {
    String encryptedText = cipher.encrypt(input, shift);
    if (encryptedText.equals(expectedOutput)) {
      System.out.printf(
          "Encryption Test Passed: Input \"%s\", Shift %d, Output \"%s\"%n", input, shift, encryptedText);
    } else {
      System.out.printf("Encryption Test Failed: Input \"%s\", Shift %d, Expected \"%s\", Got \"%s\"%n",
          input, shift, expectedOutput, encryptedText);
    }
  }

  public static void testDecryption(String input, int shift, String expectedOutput) {
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
