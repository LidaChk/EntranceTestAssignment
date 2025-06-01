package assessment.calculator;

import java.text.CharacterIterator;
import java.text.StringCharacterIterator;
import java.util.ArrayList;
import java.util.List;

import assessment.calculator.Token.TokenType;

public class TokenAnalyzer {
  private CharacterIterator it;
  public final List<Token> tokens;

  public TokenAnalyzer(String input) throws Exception {
    tokens = new ArrayList<>();
    it = new StringCharacterIterator(input.replaceAll("\\s+", ""));
    analyze();
  }

  public void analyze() throws Exception {
    while (it.current() != CharacterIterator.DONE) {
      Token token = nextToken();
      tokens.add(token);
    }
    tokens.add(new Token(TokenType.EOF));

  }

  public Token nextToken() throws Exception {

    char c = it.current();

    if (Character.isDigit(c) || c == '.') {
      StringBuilder sb = new StringBuilder();
      sb.append(c);
      it.next();
      while (it.current() != CharacterIterator.DONE && (Character.isDigit(it.current()) || it.current() == '.')) {
        sb.append(it.current());
        it.next();
      }
      return new Token(TokenType.NUMBER, sb.toString());
    }

    it.next();
    switch (c) {
      case '+':
        return new Token(TokenType.PLUS, "+");
      case '-':
        return new Token(TokenType.MINUS, "-");
      case '*':
        return new Token(TokenType.MUL, "*");
      case '/':
        return new Token(TokenType.DIV, "/");
      case '(':
        return new Token(TokenType.LPAREN, "(");
      case ')':
        return new Token(TokenType.RPAREN, ")");
      default:
        throw new Exception("Unexpected character: " + c);
    }
  }
}
