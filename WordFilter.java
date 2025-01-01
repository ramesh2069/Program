import java.util.Scanner;

public class WordFilter {

    public static boolean isWordMatching(String word, String forbiddenWord) {
        if (word.length() != forbiddenWord.length()) {
            return false;
        }
        for (int i = 0; i < word.length(); i++) {
            if (Character.toLowerCase(word.charAt(i)) != Character.toLowerCase(forbiddenWord.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    public static String maskWord(String word, char maskSymbol) {
        return "*".repeat(word.length());
    }

    public static String filterForbiddenWords(String sentence, String[] forbiddenWords, int wordCount, char maskSymbol) {
        StringBuilder filteredSentence = new StringBuilder();
        int index = 0;
        while (index < sentence.length()) {
            StringBuilder currentWord = new StringBuilder();
            while (index < sentence.length() && sentence.charAt(index) != ' ') {
                currentWord.append(sentence.charAt(index));
                index++;
            }
            boolean isForbidden = false;
            for (int i = 0; i < wordCount; i++) {
                if (isWordMatching(currentWord.toString(), forbiddenWords[i])) {
                    filteredSentence.append(maskWord(currentWord.toString(), maskSymbol));
                    isForbidden = true;
                    break;
                }
            }
            if (!isForbidden) {
                filteredSentence.append(currentWord);
            }
            if (index < sentence.length() && sentence.charAt(index) == ' ') {
                filteredSentence.append(' ');
            }
            index++;
        }
        return filteredSentence.toString();
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        try {
            System.out.println("Word Filter Program");
            String repeat;
            do {
                System.out.print("Enter a sentence: ");
                String sentence = scanner.nextLine();

                System.out.println("Enter forbidden words (one per line, empty line to stop): ");
                String[] forbiddenWords = new String[100];
                int count = 0;

                while (true) {
                    String input = scanner.nextLine();
                    if (input.isEmpty()) break;
                    forbiddenWords[count] = input;
                    count++;
                }

                System.out.print("Enter a symbol to replace forbidden words: ");
                char maskSymbol = scanner.nextLine().charAt(0);

                String result = filterForbiddenWords(sentence, forbiddenWords, count, maskSymbol);
                System.out.println("Filtered Sentence: " + result);

                System.out.print("Do you want to try again? (yes/no): ");
                repeat = scanner.nextLine();
            } while (repeat.equalsIgnoreCase("yes"));
        } finally {
            scanner.close();
        }
    }
}
