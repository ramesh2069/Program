import java.util.Scanner;

public class LeapYearChecker {
    public static Boolean isLeapYear(int year) {
        if (year >= 1582) {
            return (year % 4 == 0 && (year % 100 != 0 || year % 400 == 0));
        } else {
            return (year % 4 == 0);
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        boolean runAgain;

        System.out.println("This program checks if a year is a leap year or not.\n");

        do {
            int[] years = new int[100];
            int count = 0;

            System.out.println("Enter a set of years to check for leap year (enter a negative number to stop): ");
            while (true) {
                int year = getValidInt(sc);
                if (year < 0) {
                    break;
                }
                years[count++] = year;
            }

            System.out.println("\nLeap Year Check Results:");
            for (int i = 0; i < count; i++) {
                if (isLeapYear(years[i])) {
                    System.out.println(years[i] + " is a leap year.");
                } else {
                    System.out.println(years[i] + " is not a leap year.");
                }
            }

            System.out.print("\nDo you want to check more years? (yes/no): ");
            String response = sc.next().trim().toLowerCase();
            runAgain = response.equals("yes");
            sc.nextLine(); // Clear buffer
            System.out.println();

        } while (runAgain);

        System.out.println("Thank you for using the Leap Year Checker!");
        sc.close();
    }

    private static int getValidInt(Scanner sc) {
        while (true) {
            try {
                return sc.nextInt();
            } catch (Exception e) {
                System.out.print("Invalid input! Please enter a valid year: ");
                sc.nextLine();
            }
        }
    }
}
