import java.util.Scanner;

public class AnagramChecker {

    public static boolean areStringsAnagrams(String first, String second) {
        first = first.toLowerCase();
        second = second.toLowerCase();

        int[] firstCharCounts = new int[26];
        int[] secondCharCounts = new int[26];

        for (char c : first.toCharArray()) {
            if (c >= 'a' && c <= 'z') {
                firstCharCounts[c - 'a']++;
            }
        }

        for (char c : second.toCharArray()) {
            if (c >= 'a' && c <= 'z') {
                secondCharCounts[c - 'a']++;
            }
        }

        for (int i = 0; i < 26; i++) {
            if (firstCharCounts[i] != secondCharCounts[i]) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        try {
            System.out.println("Anagram Checker");
            String repeat;
            do {
                System.out.print("Enter the first string: ");
                String firstString = scanner.nextLine();

                System.out.print("Enter the second string: ");
                String secondString = scanner.nextLine();

                if (areStringsAnagrams(firstString, secondString)) {
                    System.out.println("The strings are anagrams.");
                } else {
                    System.out.println("The strings are not anagrams.");
                }

                System.out.print("Do you want to check again? (yes/no): ");
                repeat = scanner.nextLine();
            } while (repeat.equalsIgnoreCase("yes"));
        } finally {
            scanner.close();
        }
    }
}
