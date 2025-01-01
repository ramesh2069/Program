import java.util.Scanner;

public class PasswordValidationTool {
    public static boolean isPasswordValid(String password) {
        if (password.length() < 8) {
            return false;
        }

        int digitCount = 0;
        for (char character : password.toCharArray()) {
            if (!Character.isLetter(character) && !Character.isDigit(character)) {
                return false;
            }
            if (Character.isDigit(character)) {
                digitCount++;
            }
        }

        return digitCount >= 2;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        boolean runAgain;

        do {
            System.out.println("Password Validator");
            System.out.println("Password Rules:");
            System.out.println("1. Minimum 8 characters.");
            System.out.println("2. At least 2 digits.");
            System.out.println("3. Only letters and numbers are allowed.");

            try {
                System.out.print("Enter a password: ");
                String password = sc.nextLine();

                if (isPasswordValid(password)) {
                    System.out.println("Password is valid.");
                } else {
                    System.out.println("Password is invalid.");
                }
            } catch (Exception e) {
                System.out.println("An error occurred: " + e.getMessage());
                sc.nextLine();
            }

            System.out.print("Do you want to validate another password? (yes/no): ");
            String choice = sc.next().trim().toLowerCase();
            runAgain = choice.equals("yes");
            sc.nextLine();

        } while (runAgain);

        sc.close();
        System.out.println("Thank you for using the Password Validator!");
    }
}
