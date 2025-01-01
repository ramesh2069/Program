import java.util.Scanner;
public class SentenceExtractor {
    public static String extractSentencesContainingWord(String text, String targetWord) {
        StringBuilder sentenceBuilder = new StringBuilder();
        StringBuilder currentWordBuilder = new StringBuilder();
        boolean containsTargetWord = false;
        String extractedSentences = "";

        for (int i = 0; i < text.length(); i++) {
            char currentCharacter = text.charAt(i);

            if (Character.isLetter(currentCharacter)) {
                currentWordBuilder.append(currentCharacter);
            } else {
                if (currentWordBuilder.toString().equals(targetWord)) {
                    containsTargetWord = true;
                }
                currentWordBuilder.setLength(0);
            }

            sentenceBuilder.append(currentCharacter);

            if (currentCharacter == '.') {
                if (containsTargetWord) {
                    extractedSentences += sentenceBuilder.toString().trim() + "\n";
                }
                sentenceBuilder.setLength(0);
                containsTargetWord = false;
            }
        }

        return extractedSentences;
    }

    public static void main(String[] args) {
        Scanner inputScanner = new Scanner(System.in);
        String userChoice;

        do {
            System.out.println("Enter the text:");
            String userText = inputScanner.nextLine();

            System.out.println("Enter the word to extract sentences containing it:");
            String targetWord = inputScanner.nextLine();

            String extractedSentences = extractSentencesContainingWord(userText, targetWord);

            System.out.println("Sentences containing the word \"" + targetWord + "\":");
            System.out.println(extractedSentences);

            System.out.println("Do you want to try again with another text? (yes/no):");
            userChoice = inputScanner.nextLine();
        } while (userChoice.equalsIgnoreCase("yes"));

        inputScanner.close();
        System.out.println("Program terminated.");
    }
}
