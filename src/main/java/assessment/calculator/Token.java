package assessment.calculator;

public class Token {
  public enum TokenType {
    NUMBER, PLUS, MINUS, MUL, DIV, LPAREN, RPAREN, EOF
  }

  private final TokenType type;
  private final String value;

  public Token(TokenType type) {
    this(type, null);
  }

  public Token(TokenType type, String value) {
    this.type = type;
    this.value = value;
  }

  public TokenType getType() {
    return type;
  }

  public String getValue() {
    return value;
  }

  @Override
  public String toString() {
    return "Lexeme{" +
        "type=" + type +
        ", value='" + value + '\'' +
        '}';
  }
}
