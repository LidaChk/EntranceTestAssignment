package assessment.ui;

import java.util.Scanner;

public class ConsoleUI {
  private final Scanner scanner;
  private static final int MAX_MENU_POINT = 4;
  private static final int MIN_MENU_POINT = 1;

  public ConsoleUI() {
    this.scanner = new Scanner(System.in);
  }

  public void start() {
    boolean isRunning = true;

    System.out.println("Welcome to Gehtsoft Technical Assessment");

    while (isRunning) {
      displayMenu();
      int choice = getMenuChoice(MIN_MENU_POINT, MAX_MENU_POINT);

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
        scanner.close();
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

  private int getMenuChoice(int min, int max) {
    int choice = -1;
    boolean validInput = false;

    while (!validInput) {
      System.out.println("Enter your choice: ");
      try {
        choice = Integer.parseInt(scanner.nextLine());
        if (choice >= min && choice <= max) {
          validInput = true;
        } else {
          System.out.printf("Please enter a number between %d and %d.%n", min, max);
        }
      } catch (NumberFormatException e) {
        System.out.println("Please enter a valid number.");
      }
    }

    return choice;
  }

  private boolean continueOperation() {
    System.out.print("Continue? (y/n): ");
    String input = scanner.nextLine().trim();
    return input.equalsIgnoreCase("y") || input.equalsIgnoreCase("yes");
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
