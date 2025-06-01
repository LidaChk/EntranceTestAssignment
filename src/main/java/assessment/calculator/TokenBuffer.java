package assessment.calculator;

import java.util.List;

public class TokenBuffer {
  private int pos;

  public final List<Token> tokens;

  public TokenBuffer(List<Token> tokens) {
    this.tokens = tokens;
  }

  public Token next() {
    return tokens.get(pos++);
  }

  public void back() {
    pos--;
  }

  public int getPos() {
    return pos;
  }
}
