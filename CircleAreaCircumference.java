import java.util.Scanner;

class CircleCalculator {
    public double radius;

    public CircleCalculator(double inputRadius) {
        radius = inputRadius;
    }

    public double calculateArea() {
        return 3.14 * radius * radius;
    }

    public double calculateCircumference() {
        return 2 * 3.14 * radius;
    }
}

class CircleAreaCircumference {
    public static void main(String args[]) {
        String choice = "yes";
        Scanner sc = new Scanner(System.in);

        do {
            try {
                System.out.print("Enter the radius of the circle: ");
                double inputRadius = sc.nextDouble();
                CircleCalculator calculator = new CircleCalculator(inputRadius);
                System.out.println("Radius = " + calculator.radius + ", Area = " + calculator.calculateArea());
                System.out.println("Radius = " + calculator.radius + ", Circumference = " + calculator.calculateCircumference());
            } catch (Exception e) {
                System.out.println("Invalid input! Please enter a numeric value for the radius.");
                sc.nextLine();
                continue;
            }
            System.out.print("Would you like to calculate for another circle? Yes or No: ");
            choice = sc.next().trim().toLowerCase();
        } while (choice.equals("yes"));
        sc.close();
        System.out.println("Program ended.");
    }
}
