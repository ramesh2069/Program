import java.util.Scanner;

class Rhombus {
    private double diagonal1, diagonal2;

    public Rhombus(double diagonal1, double diagonal2) {
        this.diagonal1 = diagonal1;
        this.diagonal2 = diagonal2;
    }

    public double calculateArea() {
        return (diagonal1 * diagonal2) / 2;
    }
}

public class RhombusAreaCalculator {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String choice = "yes";

        while (choice.equalsIgnoreCase("yes")) {
            try {
                System.out.print("Enter the length of diagonal 1: ");
                double diagonal1 = sc.nextDouble();

                System.out.print("Enter the length of diagonal 2: ");
                double diagonal2 = sc.nextDouble();

                Rhombus rhombus = new Rhombus(diagonal1, diagonal2);
                System.out.println("Area: " + rhombus.calculateArea());
            } catch (Exception e) {
                System.out.println("Invalid input! Please enter numeric values for the diagonals.");
                sc.nextLine();
                continue;
            }

            System.out.print("Do you want to calculate another rhombus area? (yes/no): ");
            choice = sc.next();
        }

        System.out.println("Program terminated.");
        sc.close();
    }
}
