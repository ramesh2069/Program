import java.io.*;

public class FileDuplicator {
    public static void main(String[] args) {
        if (args.length != 2) {
            System.out.println("Usage: java FileDuplicator <source> <destination>");
            return;
        }

        File sourceFile = new File(args[0]);
        File destinationFile = new File(args[1]);

        if (!sourceFile.exists()) {
            System.out.println("Source file does not exist.");
            return;
        }

        try (InputStream inputStream = new FileInputStream(sourceFile);
             OutputStream outputStream = new FileOutputStream(destinationFile)) {

            byte[] buffer = new byte[1024];
            int bytesRead;

            while ((bytesRead = inputStream.read(buffer)) != -1) {
                outputStream.write(buffer, 0, bytesRead);
            }

            System.out.println("File copied successfully.");
        } catch (IOException e) {
            System.out.println("Error occurred while copying the file: " + e.getMessage());
        }
    }
}
