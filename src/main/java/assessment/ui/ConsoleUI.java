package assessment.ui;

import assessment.calculator.ExpressionEvaluator;
import assessment.cipher.Cipher;
import assessment.cipher.CipherBreaker;
import assessment.utils.ScannerUtils;

public class ConsoleUI {
  private static final int MAX_MENU_POINT = 5;
  private static final int MIN_MENU_POINT = 1;
  private static final Cipher cipher = new Cipher();
  private static final CipherBreaker cipherBreaker = new CipherBreaker();
  private static final ExpressionEvaluator evaluator = new ExpressionEvaluator();

  public void start() {
    boolean isRunning = true;

    System.out.println("Welcome to Gehtsoft Technical Assessment");

    while (isRunning) {
      displayMenu();
      int choice = ScannerUtils.getIntInput("Enter your choice: ", MIN_MENU_POINT, MAX_MENU_POINT);

      switch (choice) {
        case 1:
          handleCaesarEncryption();
          break;
        case 2:
          handleCaesarDecryption();
          break;
        case 3:
          handleCaesarBreaking();
          break;
        case 4:
          handleArithmeticEvaluation();
          break;
        case 5:
          isRunning = false;
          System.out.println("Exiting the application. Goodbye!");
          break;
        default:
      }

      if (isRunning && !continueOperation()) {
        isRunning = false;
        System.out.println("Exiting the application. Goodbye!");
        ScannerUtils.closeScanner();
      }
    }
  }

  private void displayMenu() {
    System.out.println("\nPlease choose an option:");
    System.out.println("1. Caesar Cipher Encryption");
    System.out.println("2. Caesar Cipher Decryption");
    System.out.println("3. Caesar Cipher Breaking (Frequency Analysis)");
    System.out.println("4. Arithmetic Expression Evaluation");
    System.out.println("5. Exit");
  }

  private boolean continueOperation() {
    return ScannerUtils.getYesNoInput("Continue? (y/n): ");
  }

  private boolean isFromFile() {
    return ScannerUtils.getYesNoInput("Read input from file? (y/n): ");
  }

  private String getInputFile() {
    return ScannerUtils.getStringInput("Enter input file path: ");
  }

  private String getOutputFile(String inputFilePath) {
    String defaultOutputFilePath = inputFilePath.substring(0, inputFilePath.length() - 4) + "_out.txt";
    return ScannerUtils.getStringInput("Enter output file path (default " + defaultOutputFilePath + "): ");
  }

  private int getShift() {
    return ScannerUtils.getIntInput("Enter shift value: ", Integer.MIN_VALUE, Integer.MAX_VALUE);
  }

  private void invokeFileProcessing(String mode) {
    String inputFilePath = getInputFile();
    String outputFilePath = getOutputFile(inputFilePath);

    try {
      cipher.processFile(inputFilePath, outputFilePath, mode, getShift());
    } catch (Exception e) {
      System.out.println("Error processing file: " + e.getMessage());
    }
  }

  private void handleCaesarEncryption() {
    System.out.println("Caesar Cipher Encryption");

    if (isFromFile()) {
      invokeFileProcessing("encrypt");
      return;
    }

    String plainText = ScannerUtils.getStringInput("Enter text to encrypt: ");

    try {
      String encryptedText = cipher.encrypt(plainText, getShift());
      System.out.println("Encrypted text: " + encryptedText);
    } catch (Exception e) {
      System.out.println("Error encrypting text: " + e.getMessage());
    }

  }

  private void handleCaesarDecryption() {
    System.out.println("Caesar Cipher Decryption");
    String cipherText = ScannerUtils.getStringInput("Enter text to decrypt: ");
    int shift = ScannerUtils.getIntInput("Enter shift value: ", Integer.MIN_VALUE, Integer.MAX_VALUE);
    try {
      String decryptedText = cipher.decrypt(cipherText, shift);
      System.out.println("Decrypted text: " + decryptedText);
    } catch (Exception e) {
      System.out.println("Error decrypting text: " + e.getMessage());
    }
  }

  private void handleCaesarBreaking() {
    System.out.println("Caesar Cipher Breaking");

    if (isFromFile()) {
      String inputFilePath = getInputFile();
      String outputFilePath = getOutputFile(inputFilePath);

      try {
        cipherBreaker.processFile(inputFilePath, outputFilePath);
      } catch (Exception e) {
        System.out.println("Error encrypting file: " + e.getMessage());
      }
      return;
    }
    String cipherText = ScannerUtils.getStringInput("Enter encrypted text: ");

    if (cipherText.isEmpty()) {
      System.out.println("Decrypted text: ");
      return;
    }

    try {
      String decryptedText = cipherBreaker.breakCaesar(cipherText);
      System.out.println("Decrypted text: " + decryptedText);
    } catch (Exception e) {
      System.out.println("Error breaking cipher: " + e.getMessage());
    }
  }

  private void handleArithmeticEvaluation() {
    System.out.println("Arithmetic Expression Evaluation");
    String expression = ScannerUtils.getStringInput("Expression: ");

    try {
      System.out.println("Result: " + evaluator.evaluate(expression));
    } catch (Exception e) {
      System.out.println("Error evaluating expression: " + e.getMessage());
    }
  }
}
