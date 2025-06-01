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
      if (token.getType() == TokenType.MINUS && isUnaryMinus()) {
        // Unary minus -x --> ( 0 - x )
        tokens.add(new Token(TokenType.LPAREN, "("));
        tokens.add(new Token(TokenType.NUMBER, "0"));
        tokens.add(token);
        Token numberToken = nextToken();
        tokens.add(numberToken);
        tokens.add(new Token(TokenType.RPAREN, ")"));
      } else {
        tokens.add(token);
      }
    }

  }

  public Token nextToken() throws Exception {
    if (it.current() == CharacterIterator.DONE) {
      return new Token(TokenType.EOF);
    }

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

  private boolean isUnaryMinus() {

    if (tokens.isEmpty()) {
      return true;
    }

    Token prevToken = tokens.get(tokens.size() - 1);
    return prevToken.getType() == TokenType.LPAREN ||
        prevToken.getType() == TokenType.PLUS ||
        prevToken.getType() == TokenType.MINUS ||
        prevToken.getType() == TokenType.DIV ||
        prevToken.getType() == TokenType.MUL;
  }
}
