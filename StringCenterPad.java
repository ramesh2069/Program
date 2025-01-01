import java.util.Scanner;

public class StringCenterPad {
    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);
        String str;
        char paddingChar;
        String tryAgain;

        do {
            System.out.println("\nEnter a string of 20 characters or less (or type 'exit' to quit):");
            str = sc.next();

            if(str.equalsIgnoreCase("exit")) {
                System.out.println("\nProgram terminated.");
                break;
            }

            if(str.length() <= 20) {
                System.out.println("\nEnter a character to pad with:");
                paddingChar = sc.next().charAt(0);

                int padding = (20 - str.length())/2;
                StringBuilder filledString = new StringBuilder();

                for(int i = 0; i < padding; i++) {
                    filledString.append(paddingChar);
                }
                filledString.append(str);
                for(int i = 0; i < padding; i++) {
                    filledString.append(paddingChar);
                }

                System.out.println("\nHere is you centered and padded string:");
                System.out.println(filledString.toString());
                
                System.out.println("\nDo you want to try again with another string? (yes/no)");
                tryAgain = sc.next();

                if(!tryAgain.equalsIgnoreCase("yes")) {
                    System.out.println("\nProgram terminated.");
                    break;
                }
            } else {
                System.out.println("\nError: String is too long. Please try again.");
            }

            sc.close();
        } while(true);
    }
}
