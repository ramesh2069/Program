class VowelDetectedException extends Exception {
    public VowelDetectedException(String message) {
        super(message);
    }
}

class BlankInputException extends Exception {
    public BlankInputException(String message) {
        super(message);
    }
}

class ExitCharacterException extends Exception {
    public ExitCharacterException(String message) {
        super(message);
    }
}

public class CharacterValidator {
    public static void main(String[] args) {
        if (args.length < 1) {
            System.out.println("Please provide a character as a command-line argument.");
            return;
        }

        String input = args[0];

        try {
            if (input.isBlank()) {
                throw new BlankInputException("The input is blank.");
            }

            char character = input.charAt(0);

            if ("AEIOUaeiou".indexOf(character) != -1) {
                throw new VowelDetectedException("The input is a vowel.");
            }

            if (character == 'X' || character == 'x') {
                throw new ExitCharacterException("Exiting program.");
            }

            System.out.println("The character is valid.");
        } catch (VowelDetectedException e) {
            System.out.println("Error: " + e.getMessage());
        } catch (BlankInputException e) {
            System.out.println("Error: " + e.getMessage());
        } catch (ExitCharacterException e) {
            System.out.println("Error: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("An unexpected error occurred: " + e.getMessage());
        }
    }
}
