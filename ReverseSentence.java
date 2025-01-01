import java.util.Scanner;

public class ReverseSentence {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        boolean continueProgram = true;

        while (continueProgram) {
            System.out.print("Enter a sentence: ");
            String sentence = sc.nextLine();

            if (sentence.trim().isEmpty()) {
                System.out.println("Invalid input! Please enter a non-empty sentence.");
                continue;
            }

            System.out.print("Reversed sentence: ");
            printReversedSentence(sentence);

            System.out.print("\nDo you want to reverse another sentence? (yes/no): ");
            String userChoice = sc.nextLine();
            if (!userChoice.equalsIgnoreCase("yes")) {
                continueProgram = false;
            }
        }

        System.out.println("Program terminated.");
        sc.close();
    }

    public static void printReversedSentence(String sentence) {
        for (int i = sentence.length() - 1; i >= 0; i--) {
            System.out.print(sentence.charAt(i));
        }
    }
}