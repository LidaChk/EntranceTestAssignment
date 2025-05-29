package assessment.ui;

import assessment.utils.ScannerUtils;

public class ConsoleUI {
  private static final int MAX_MENU_POINT = 4;
  private static final int MIN_MENU_POINT = 1;

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
          handleArithmeticEvaluation();
          break;
        case 4:
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
    System.out.println("3. Arithmetic Expression Evaluation");
    System.out.println("4. Exit");
  }

  private boolean continueOperation() {
    return ScannerUtils.getYesNoInput("Continue? (y/n): ");
  }

  private void handleCaesarEncryption() {
    // TODO CaesarEncryption
    System.out.println("CaesarEncryption");
  }

  private void handleCaesarDecryption() {
    // TODO CaesarDecryption
    System.out.println("CaesarDecryption");
  }

  private void handleArithmeticEvaluation() {
    // TODO ArithmeticEvaluation
    System.out.println("ArithmeticEvaluation");
  }
}
