import java.util.Scanner;

class RainbowColor {
    public void findColor(char colorCode) {
        if (!Character.isAlphabetic(colorCode)) {
            throw new IllegalArgumentException("Input is not an alphabet.");
        }

        switch (Character.toUpperCase(colorCode)) {
            case 'R' -> System.out.println("Red");
            case 'O' -> System.out.println("Orange");
            case 'Y' -> System.out.println("Yellow");
            case 'G' -> System.out.println("Green");
            case 'B' -> System.out.println("Blue");
            case 'I' -> System.out.println("Indigo");
            case 'V' -> System.out.println("Violet");
            default -> throw new IllegalArgumentException("Invalid color character.");
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        RainbowColor rainbowColor = new RainbowColor();
        boolean continueChecking;

        do {
            System.out.println("Rainbow Color Finder");
            System.out.println("Enter a character representing a rainbow color (R, O, Y, G, B, I, V):");

            try {
                System.out.print("Enter a character: ");
                char colorCode = sc.next().charAt(0);

                rainbowColor.findColor(colorCode);
            } catch (IllegalArgumentException e) {
                System.out.println("Error: " + e.getMessage());
            }

            System.out.print("Do you want to check another color? (yes/no): ");
            String response = sc.next().trim().toLowerCase();
            continueChecking = response.equals("yes");
            sc.nextLine();
        } while (continueChecking);

        sc.close();
        System.out.println("Thank you for using the Rainbow Color Finder!");
    }
}
