import java.util.StringTokenizer;
import java.util.Scanner;

public class ParagraphAnalyzer {
    public static void main(String[] args) {
        Scanner inputScanner = new Scanner(System.in);
        String userChoice = "yes";

        while (userChoice.equalsIgnoreCase("yes")) {
            System.out.println("Enter the paragraph (Press Enter on an empty line to finish):");
            StringBuilder paragraphBuilder = new StringBuilder();
            String currentLine;

            while (!(currentLine = inputScanner.nextLine()).isEmpty()) {
                paragraphBuilder.append(currentLine).append("\n");
            }

            String paragraphText = paragraphBuilder.toString();
            int characterCount = paragraphText.length();
            int vowelCount = countVowels(paragraphText);
            int wordCount = countWords(paragraphText);
            int lineCount = countLines(paragraphText);
            int sentenceCount = countSentences(paragraphText);
            String vowelPositions = getVowelPositions(paragraphText);

            System.out.println("Character count: " + characterCount);
            System.out.println("Vowel count: " + vowelCount);
            System.out.println("Vowel positions: " + vowelPositions.trim());
            System.out.println("Word count: " + wordCount);
            System.out.println("Line count: " + lineCount);
            System.out.println("Sentence count: " + sentenceCount);

            System.out.println("Do you want to analyze another paragraph? (yes/no):");
            userChoice = inputScanner.nextLine().trim();
        }

        inputScanner.close();
        System.out.println("Program terminated.");
    }

    private static int countVowels(String text) {
        int vowelCount = 0;
        String vowels = "AEIOUaeiou";
        for (int i = 0; i < text.length(); i++) {
            if (vowels.indexOf(text.charAt(i)) != -1) {
                vowelCount++;
            }
        }
        return vowelCount;
    }

    private static int countWords(String text) {
        StringTokenizer words = new StringTokenizer(text);
        return words.countTokens();
    }

    private static int countLines(String text) {
        StringTokenizer lines = new StringTokenizer(text, "\n");
        return lines.countTokens();
    }

    private static int countSentences(String text) {
        StringTokenizer sentences = new StringTokenizer(text, ".!?\n");
        return sentences.countTokens();
    }

    private static String getVowelPositions(String text) {
        StringBuilder vowelPositions = new StringBuilder();
        String vowels = "AEIOUaeiou";
        for (int i = 0; i < text.length(); i++) {
            if (vowels.indexOf(text.charAt(i)) != -1) {
                vowelPositions.append(i + 1).append(" ");
            }
        }
        return vowelPositions.toString();
    }
}
