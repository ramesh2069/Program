import java.util.Scanner;

public class UniqueCharacterCounter {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        try {
            System.out.println("Unique Character Counter");
            String repeat;
            do {
                System.out.print("Enter a string: ");
                String input = scanner.nextLine();

                String uniqueCharacters = findUniqueCharacters(input);
                String characterCounts = countCharacterOccurrences(input);

                System.out.println("Unique Characters: " + uniqueCharacters);
                System.out.println("Character Counts: " + characterCounts);

                System.out.print("Do you want to try again? (yes/no): ");
                repeat = scanner.nextLine();
            } while (repeat.equalsIgnoreCase("yes"));
        } finally {
            scanner.close();
        }
    }

    public static String findUniqueCharacters(String input) {
        StringBuilder uniqueChars = new StringBuilder();
        for (char c : input.toCharArray()) {
            if (uniqueChars.indexOf(String.valueOf(c)) == -1) {
                uniqueChars.append(c);
            }
        }
        return uniqueChars.toString();
    }

    public static String countCharacterOccurrences(String input) {
        StringBuilder characterCount = new StringBuilder();
        for (char c : input.toCharArray()) {
            if (characterCount.indexOf(String.valueOf(c)) == -1) {
                int count = 0;
                for (char ch : input.toCharArray()) {
                    if (ch == c) count++;
                }
                characterCount.append(c).append(count);
            }
        }
        return characterCount.toString();
    }
}
