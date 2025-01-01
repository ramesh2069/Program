import java.util.Scanner;

public class ReverseWords {
    public static void main(String[] args) {
        Scanner inputScanner = new Scanner(System.in);
        String userChoice = "yes";

        while (userChoice.equalsIgnoreCase("yes")) {
            System.out.println("Enter a sentence:");
            String userSentence = inputScanner.nextLine().trim();

            if (!userSentence.isEmpty()) {
                System.out.println("Reversed sentence:");
                System.out.println(reverseWords(userSentence));
            } else {
                System.out.println("Please enter a valid sentence.");
            }

            System.out.println("Do you want to try again with another sentence? (yes/no):");
            userChoice = inputScanner.nextLine().trim();
        }

        inputScanner.close();
        System.out.println("Program terminated.");
    }

    public static String reverseWords(String sentence) {
        StringBuilder reversedSentence = new StringBuilder();
        StringBuilder currentWord = new StringBuilder();

        for (int i = sentence.length() - 1; i >= 0; i--) {
            char currentChar = sentence.charAt(i);

            if (currentChar != ' ') {
                currentWord.insert(0, currentChar);
            } else {
                reversedSentence.append(currentWord).append(" ");
                currentWord.setLength(0);
            }
        }

        reversedSentence.append(currentWord);
        return reversedSentence.toString();
    }
}
