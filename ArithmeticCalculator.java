class InsufficientArgumentsException extends Exception {
    public InsufficientArgumentsException(String message) {
        super(message);
    }
}

class InvalidOperatorException extends Exception {
    public InvalidOperatorException(String message) {
        super(message);
    }
}

class NegativeResultException extends Exception {
    public NegativeResultException(String message) {
        super(message);
    }
}

public class ArithmeticCalculator {
    public static void main(String[] args) {
        try {
            if (args.length < 3) {
                throw new InsufficientArgumentsException("Please provide two numbers and an operator.");
            }

            int firstNumber = Integer.parseInt(args[0]);
            int secondNumber = Integer.parseInt(args[1]);
            char operator = args[2].charAt(0);

            int result = calculate(firstNumber, secondNumber, operator);

            if (result < 0) {
                throw new NegativeResultException("The result is negative.");
            }

            System.out.println("Result: " + result);
        } catch (InsufficientArgumentsException e) {
            System.out.println("Error: " + e.getMessage());
        } catch (InvalidOperatorException e) {
            System.out.println("Error: " + e.getMessage());
        } catch (NegativeResultException e) {
            System.out.println("Error: " + e.getMessage());
        } catch (NumberFormatException e) {
            System.out.println("Please provide valid integers for the numbers.");
        } catch (Exception e) {
            System.out.println("An unexpected error occurred: " + e.getMessage());
        }
    }

    public static int calculate(int number1, int number2, char operator) throws InvalidOperatorException {
        return switch (operator) {
            case '+' -> number1 + number2;
            case '-' -> number1 - number2;
            case '*' -> number1 * number2;
            case '/' -> {
                if (number2 == 0) throw new ArithmeticException("Cannot divide by zero.");
                yield number1 / number2;
            }
            default -> throw new InvalidOperatorException("Supported operators are: +, -, *, /.");
        };
    }
}
