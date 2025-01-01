import java.util.Scanner;

public class TextReplacer {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        try {
            System.out.println("Find and Replace Program");
            String repeat;
            do {
                System.out.print("Enter the text: ");
                String text = scanner.nextLine();

                System.out.print("Enter the word to find: ");
                String wordToFind = scanner.nextLine();

                System.out.print("Enter the word to replace with: ");
                String replacementWord = scanner.nextLine();

                String modifiedText = replaceWord(text, wordToFind, replacementWord);
                int occurrences = countOccurrences(text, wordToFind);

                System.out.println("Modified Text: " + modifiedText);
                System.out.println("Occurrences of '" + wordToFind + "': " + occurrences);

                System.out.print("Do you want to try again? (yes/no): ");
                repeat = scanner.nextLine();
            } while (repeat.equalsIgnoreCase("yes"));
        } finally {
            scanner.close();
        }
    }

    public static String replaceWord(String text, String findWord, String replaceWord) {
        return text.replaceAll("\\b" + findWord + "\\b", replaceWord);
    }

    public static int countOccurrences(String text, String word) {
        String[] words = text.split("\\s+");
        int count = 0;
        for (String currentWord : words) {
            if (currentWord.equals(word)) count++;
        }
        return count;
    }
}
