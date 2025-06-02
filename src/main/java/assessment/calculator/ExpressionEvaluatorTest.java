package assessment.calculator;

public class ExpressionEvaluatorTest {

    public static void main(String[] args) {
        ExpressionEvaluator evaluator = new ExpressionEvaluator();

        testCase("2 + 3", 5, evaluator);
        testCase("----2", 2, evaluator);
        testCase("-(1+1)", -2, evaluator);
        testCase("2 + 3 * 4", 14, evaluator);
        testCase("(10 + 5) / 3", 5, evaluator);
        testCase("2 * (3 + 4) - 1", 13, evaluator);
        testCase("-5 + 3", -2.0, evaluator);
        testCase("2.5 + 3", 5.5, evaluator);
        testCase("4.5 / 3", 1.5, evaluator);
        testCase("2 * (3 + (4 * 2))", 22, evaluator);
        testCase("-5 - 3", -8, evaluator);
        testCase("-3 * 5", -15, evaluator);
        testCase("2 * (3 + (4 * 2)) + (5 - 3)", 24, evaluator);
        testCase("", 0, evaluator);

        testDivisionByZero("10 / 0", evaluator);

    }

    public static void testCase(String expression, double expected, ExpressionEvaluator evaluator) {
        try {
            double result = evaluator.evaluate(expression);
            if (result == expected) {
                System.out.printf("Test passed for expression: \"%s\"%n", expression);
            } else {
                System.out.printf("Test failed for expression: \"%s\". Expected: %f, Got: %f%n", expression, expected,
                        result);
            }
        } catch (Exception e) {
            System.out.printf("Test failed for expression: \"%s\" with exception: %s%n", expression, e.getMessage());
        }
    }

    public static void testDivisionByZero(String expression, ExpressionEvaluator evaluator) {
        try {
            evaluator.evaluate(expression);
            System.out.printf("Test failed for expression: \"%s\". Division by zero was not caught.%n", expression);
        } catch (ArithmeticException e) {
            System.out.printf("Test passed for expression: \"%s\". Division by zero was caught.%n", expression);
        } catch (Exception e) {
            if (e.getMessage().contains("Division by zero")) {
                System.out.printf("Test passed for expression: \"%s\". Division by zero was caught.%n", expression);
            } else {
                System.out.printf("Test failed for expression: \"%s\" with unexpected exception: %s%n", expression,
                        e.getMessage());
            }
        }
    }
}
