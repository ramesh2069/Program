//javac mypackage/PalindromeChecker.java
//javac TestPalindrome.java
//java TestPalindrome

import mypackage.StringPalindromeChecker;
import java.util.Scanner;

public class PalindromeTester {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        StringPalindromeChecker checker = new StringPalindromeChecker();

        System.out.println("Palindrome Checker");

        while (true) {
            try {
                System.out.print("Enter a string: ");
                String input = sc.nextLine();

                if (input.trim().isEmpty()) {
                    throw new IllegalArgumentException("Input cannot be empty. Please provide a valid string.");
                }

                if (checker.isPalindrome(input)) {
                    System.out.println("The string \"" + input + "\" is a palindrome.");
                } else {
                    System.out.println("The string \"" + input + "\" is not a palindrome.");
                }

                System.out.print("Do you want to check another string? (yes/no): ");
                String choice = sc.nextLine().trim().toLowerCase();
                if (!choice.equals("yes")) {
                    System.out.println("Thank you for using the Palindrome Checker!");
                    break;
                }

            } catch (IllegalArgumentException e) {
                System.out.println("Error: " + e.getMessage());
            } catch (Exception e) {
                System.out.println("An unexpected error occurred: " + e.getMessage());
            }
        }
        sc.close();
    }
}
package mypackage;

public class StringPalindromeChecker {
    public boolean isPalindrome(String inputString) {
        if (inputString == null) {
            return false;
        }
        inputString = inputString.replaceAll("\\s+", "").toLowerCase();
        int left = 0, right = inputString.length() - 1;
        while (left < right) {
            if (inputString.charAt(left) != inputString.charAt(right)) {
                return false;
            }
            left++;
            right--;
        }
        return true;
    }
}
