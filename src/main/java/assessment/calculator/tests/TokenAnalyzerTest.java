package assessment.calculator.tests;

import java.util.List;

import assessment.calculator.Token.TokenType;
import assessment.calculator.Token;
import assessment.calculator.TokenAnalyzer;

public class TokenAnalyzerTest {

  public static void main(String[] args) throws Exception {
    testAnalyzeValidExpression();
    testAnalyzeInvalidExpression("abc", "Unexpected character: a");

  }

  public static void testAnalyzeValidExpression() throws Exception {
    System.out.println("Testing testAnalyzeValidExpression...");
    String expression = "10.01 + 20 * (30 / 5)";
    TokenAnalyzer analyzer = new TokenAnalyzer(expression);
    List<Token> tokens = analyzer.tokens;

    System.out.println("expression: " + expression);
    for (Token token : tokens) {
      System.out.println(token.toString());
    }

    if (tokens.size() == 10 &&
        tokens.get(0).getType() == TokenType.NUMBER && tokens.get(0).getValue().equals("10.01") &&
        tokens.get(1).getType() == TokenType.PLUS &&
        tokens.get(2).getType() == TokenType.NUMBER && tokens.get(2).getValue().equals("20") &&
        tokens.get(3).getType() == TokenType.MUL &&
        tokens.get(4).getType() == TokenType.LPAREN &&
        tokens.get(5).getType() == TokenType.NUMBER && tokens.get(5).getValue().equals("30") &&
        tokens.get(6).getType() == TokenType.DIV &&
        tokens.get(7).getType() == TokenType.NUMBER && tokens.get(7).getValue().equals("5") &&
        tokens.get(8).getType() == TokenType.RPAREN &&
        tokens.get(9).getType() == TokenType.EOF) {
      System.out.println("testAnalyzeValidExpression Test passed!");
    } else {
      System.out.println("testAnalyzeValidExpression failed!");
    }
  }

  public static void testAnalyzeInvalidExpression(String expression, String expectedErrorMessage) {
    System.out.println("\nTesting testAnalyzeInvalidExpression...");
    try {
      new TokenAnalyzer(expression);
      System.out.println("testAnalyzeInvalidExpression failed: No exception thrown for expression: " + expression);
    } catch (Exception e) {
      if (e.getMessage().contains(expectedErrorMessage)) {
        System.out.println("testAnalyzeInvalidExpression Test passed for expression: " + expression);
      } else {
        System.out.println("testAnalyzeInvalidExpression failed for expression: " + expression
            + ". Expected error message containing: '" + expectedErrorMessage + "' but got: '" + e.getMessage() + "'");
        e.printStackTrace();
      }

    }
  }

}
