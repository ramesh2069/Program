import java.util.Scanner;

public class AverageCalculator {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        boolean runAgain = true; // Initialize the variable before use.

        do {
            System.out.println("Average and Count Program");

            try {
                System.out.print("Enter the number of elements: ");
                int numberOfElements = sc.nextInt();

                if (numberOfElements <= 0) {
                    System.out.println("Please enter a positive number.");
                    continue;
                }

                double[] elements = new double[numberOfElements];
                System.out.println("Enter " + numberOfElements + " numbers:");
                for (int i = 0; i < numberOfElements; i++) {
                    elements[i] = sc.nextDouble();
                }

                double sum = 0;
                for (double number : elements) {
                    sum += number;
                }
                double average = sum / numberOfElements;

                int countAboveAverage = 0;
                for (double number : elements) {
                    if (number > average) {
                        countAboveAverage++;
                    }
                }

                System.out.printf("Average: %.2f%n", average);
                System.out.println("Numbers greater than average: " + countAboveAverage);
            } catch (Exception e) {
                System.out.println("An error occurred: " + e.getMessage());
                sc.nextLine();
            }

            System.out.print("Do you want to run the program again? (yes/no): ");
            String choice = sc.next().trim().toLowerCase();
            runAgain = choice.equals("yes");

        } while (runAgain);

        sc.close();
        System.out.println("Thank you for using the Average Calculator!");
    }
}
