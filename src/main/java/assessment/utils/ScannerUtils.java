package assessment.utils;

import java.util.Scanner;

public class ScannerUtils {
  private static final Scanner scanner = new Scanner(System.in);

  private ScannerUtils() {
  }

  public static int getIntInput(String prompt, int min, int max) {
    int value = -1;
    boolean validInput = false;

    while (!validInput) {
      System.out.print(prompt);
      try {
        value = Integer.parseInt(scanner.nextLine());
        if (value >= min && value <= max) {
          validInput = true;
        } else {
          System.out.printf("Please enter a number between %d and %d.%n", min, max);
        }
      } catch (NumberFormatException e) {
        System.out.println("Please enter a valid number.");
      }
    }

    return value;
  }

  public static boolean getYesNoInput(String prompt) {
    System.out.print(prompt);
    String input = scanner.nextLine().trim();
    return input.equalsIgnoreCase("y") || input.equalsIgnoreCase("yes");
  }

  public static String getStringInput(String prompt) {
    System.out.print(prompt);
    return scanner.nextLine().trim();
  }

  public static void closeScanner() {
    scanner.close();
  }
}
