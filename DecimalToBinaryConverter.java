import java.util.Scanner;

public class DecimalToBinaryConverter {

    public static String convertDecimalToBinary(int decimalNumber) {
        StringBuilder binaryRepresentation = new StringBuilder();

        if (decimalNumber == 0) {
            return "0"; // Special case for 0
        }

        while (decimalNumber > 0) {
            int remainder = decimalNumber % 2;
            binaryRepresentation.insert(0, remainder); // Append remainder to the start
            decimalNumber /= 2;
        }

        return binaryRepresentation.toString();
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean runAgain = false;

        System.out.println("Decimal to Binary Converter");
        System.out.println("This program converts a decimal number to its binary equivalent.");

        do {
            try {
                System.out.print("Enter a non-negative decimal number: ");
                int inputDecimal = scanner.nextInt();

                if (inputDecimal < 0) {
                    System.out.println("Negative numbers are not allowed. Please enter a non-negative number.");
                    continue;
                }

                String binaryResult = convertDecimalToBinary(inputDecimal);
                System.out.println("Binary representation of " + inputDecimal + " is: " + binaryResult);
            } catch (Exception e) {
                System.out.println("Invalid input. Please enter a valid integer.");
                scanner.next(); // Clear invalid input
            }

            System.out.print("Would you like to convert another number? (yes/no): ");
            String userResponse = scanner.next().trim().toLowerCase();
            runAgain = userResponse.equals("yes");
            scanner.nextLine(); // Clear buffer
            System.out.println();
        } while (runAgain);

        System.out.println("Program exited. Thank you for using the converter.");
        scanner.close();
    }
}
