import java.util.Scanner;

abstract class InterestCalculator {
    protected double principal, rate, time;

    public InterestCalculator(double principal, double rate, double time) {
        this.principal = principal;
        this.rate = rate;
        this.time = time;
    }

    public abstract double calculateInterest();
}

class SimpleInterest extends InterestCalculator {
    public SimpleInterest(double principal, double rate, double time) {
        super(principal, rate, time);
    }

    @Override
    public double calculateInterest() {
        return (principal * rate * time) / 100;
    }
}

class CompoundInterest extends InterestCalculator {
    public CompoundInterest(double principal, double rate, double time) {
        super(principal, rate, time);
    }

    @Override
    public double calculateInterest() {
        return principal * Math.pow((1 + rate / 100), time) - principal;
    }
}

public class InterestCalculatorProgram {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        boolean continueProgram = true;

        while (continueProgram) {
            System.out.print("Enter principal amount: ");
            double principal = getValidDouble(sc);

            System.out.print("Enter rate of interest: ");
            double rate = getValidDouble(sc);

            System.out.print("Enter time period in years: ");
            double time = getValidDouble(sc);

            System.out.println("1. Simple Interest");
            System.out.println("2. Compound Interest");
            System.out.print("Choose interest type: ");
            int choice = getValidInt(sc);

            InterestCalculator calculator = null;
            if (choice == 1) {
                calculator = new SimpleInterest(principal, rate, time);
            } else if (choice == 2) {
                calculator = new CompoundInterest(principal, rate, time);
            } else {
                System.out.println("Invalid choice.");
                continue;
            }

            System.out.println("Calculated Interest: " + calculator.calculateInterest());

            System.out.print("Do you want to calculate another interest? (yes/no): ");
            String userChoice = sc.next();
            if (!userChoice.equalsIgnoreCase("yes")) {
                continueProgram = false;
            }
        }

        System.out.println("Program terminated.");
        sc.close();
    }

    private static int getValidInt(Scanner sc) {
        while (true) {
            try {
                return sc.nextInt();
            } catch (Exception e) {
                System.out.print("Invalid input! Enter a valid integer: ");
                sc.nextLine();
            }
        }
    }

    private static double getValidDouble(Scanner sc) {
        while (true) {
            try {
                return sc.nextDouble();
            } catch (Exception e) {
                System.out.print("Invalid input! Enter a valid number: ");
                sc.nextLine();
            }
        }
    }
}
