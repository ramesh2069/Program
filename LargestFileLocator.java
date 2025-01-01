import java.io.File;
import java.util.Scanner;

public class LargestFileLocator {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        boolean repeatProcess = false;

        System.out.println("Largest File Locator");
        System.out.println("This program identifies the largest file in a given directory.");

        do {
            System.out.print("Enter the directory path: ");
            String directoryPath = sc.nextLine();
            File directory = new File(directoryPath);

            if (!directory.isDirectory()) {
                System.out.println("Invalid directory path.");
                continue;
            }

            File largestFile = null;
            long largestFileSize = 0;

            try {
                File[] files = directory.listFiles();
                if (files != null) {
                    for (File file : files) {
                        if (file.isFile() && file.length() > largestFileSize) {
                            largestFileSize = file.length();
                            largestFile = file;
                        }
                    }
                } else {
                    System.out.println("Unable to read files from the directory.");
                    continue;
                }
            } catch (SecurityException e) {
                System.out.println("Access denied to the directory: " + e.getMessage());
                continue;
            } catch (NullPointerException e) {
                System.out.println("An unexpected error occurred: " + e.getMessage());
                continue;
            }

            if (largestFile != null) {
                System.out.println("Largest file: " + largestFile.getName() + " (" + largestFileSize + " bytes)");
            } else {
                System.out.println("No files found in the directory.");
            }

            System.out.print("Do you want to check another directory? (yes/no): ");
            String userResponse = sc.next().trim().toLowerCase();
            repeatProcess = userResponse.equals("yes");
            sc.nextLine();
        } while (repeatProcess);

        sc.close();
        System.out.println("Thank you for using the Largest File Locator!");
    }
}
