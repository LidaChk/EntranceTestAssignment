package assessment.calculator;

import assessment.calculator.Token.TokenType;

public class ExpressionEvaluator {

    // expression --> term (('+' | '-') term)*
    // term --> factor (('*' | '/') factor)*
    // factor --> number | '(' expression ')' | '-' factor
    // number --> [0-9]+ ('.' [0-9]+)?

    public double evaluate(String expression) throws Exception {
        TokenAnalyzer tokenAnalyzer = new TokenAnalyzer(expression);
        return expression(new TokenBuffer(tokenAnalyzer.tokens));
    }

    public static double expression(TokenBuffer tokens) throws Exception {
        Token token = tokens.next();
        if (token.getType() == TokenType.EOF) {
            return 0;
        } else {
            tokens.back();
            return plusminus(tokens);
        }
    }

    public static double plusminus(TokenBuffer tokens) throws Exception {
        double value = multdiv(tokens);
        while (true) {
            Token token = tokens.next();
            switch (token.getType()) {
                case PLUS:
                    value += multdiv(tokens);
                    break;
                case MINUS:
                    value -= multdiv(tokens);
                    break;
                case EOF, RPAREN:
                    tokens.back();
                    return value;
                default:
                    throw new Exception("Unexpected token: " + token.getValue()
                            + " at position: " + tokens.getPos());
            }
        }
    }

    public static double multdiv(TokenBuffer tokens) throws Exception {
        double value = factor(tokens);
        while (true) {
            Token token = tokens.next();
            switch (token.getType()) {
                case MUL:
                    value *= factor(tokens);
                    break;
                case DIV:
                    double divisor = factor(tokens);
                    if (divisor == 0) {
                        throw new Exception("Division by zero");
                    }
                    value /= divisor;
                    break;
                case EOF, RPAREN, PLUS, MINUS:
                    tokens.back();
                    return value;
                default:
                    throw new Exception("Unexpected token: " + token.getValue()
                            + " at position: " + tokens.getPos());
            }
        }
    }

    public static double factor(TokenBuffer tokens) throws Exception {
        Token token = tokens.next();
        switch (token.getType()) {
            case NUMBER:
                return Double.parseDouble(token.getValue());
            case LPAREN:
                double value = plusminus(tokens);
                token = tokens.next();
                if (token.getType() != TokenType.RPAREN) {
                    throw new Exception("Unexpected token: " + token.getValue()
                            + " at position: " + tokens.getPos());
                }
                return value;
            case MINUS:
                return -factor(tokens);
            default:
                throw new Exception("Unexpected token: " + token.getValue()
                        + " at position: " + tokens.getPos());
        }
    }

}
