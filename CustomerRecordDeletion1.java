import java.sql.*;
import java.util.Scanner;

public class CustomerRecordDeletion {
    public static void main(String[] args) {
        String dbServer = "172.16.20.20"; // Add server name
        String dbPort = "1521";
        String dbUsername = "c##mcaorc42";
        String dbPassword = "mcaorc42";
        String dbSid = "orcl";
        String dbUrl = "jdbc:oracle:thin:@//" + dbServer + ":" + dbPort + "/" + dbSid;

        Scanner scanner = new Scanner(System.in);
        boolean continueProgram;

        System.out.println("Customer Record Deletion Program");
        System.out.println("This program deletes customer records from the database.");

        do {
            int customerId = 0;
            boolean isValidInput = false;

            // Validate customer ID input
            while (!isValidInput) {
                try {
                    System.out.print("Enter the Customer ID to delete: ");
                    customerId = scanner.nextInt();
                    isValidInput = true;
                } catch (Exception e) {
                    System.out.println("Invalid input. Please enter a valid Customer ID (integer).");
                    scanner.next(); // Clear invalid input
                }
            }

            try {
                Class.forName("oracle.jdbc.driver.OracleDriver");
                Connection connection = DriverManager.getConnection(dbUrl, dbUsername, dbPassword);

                String checkQuery = "SELECT COUNT(*) FROM customer WHERE id = ?";
                PreparedStatement checkStatement = connection.prepareStatement(checkQuery);
                checkStatement.setInt(1, customerId);
                ResultSet resultSet = checkStatement.executeQuery();

                if (resultSet.next() && resultSet.getInt(1) > 0) {
                    String deleteQuery = "DELETE FROM customer WHERE id = ?";
                    PreparedStatement deleteStatement = connection.prepareStatement(deleteQuery);
                    deleteStatement.setInt(1, customerId);

                    int rowsDeleted = deleteStatement.executeUpdate();
                    if (rowsDeleted > 0) {
                        System.out.println("Customer record deleted successfully.");
                    } else {
                        System.out.println("Customer record deletion failed.");
                    }

                    deleteStatement.close();
                } else {
                    System.out.println("No customer found with the given ID.");
                }

                checkStatement.close();
                connection.close();
            } catch (ClassNotFoundException e) {
                System.out.println("JDBC Driver not found. Please ensure it is configured correctly.");
                e.printStackTrace();
            } catch (SQLException e) {
                System.out.println("Database error: " + e.getMessage());
                e.printStackTrace();
            }

            System.out.print("Do you want to delete another customer record? (yes/no): ");
            String userResponse = scanner.next().trim().toLowerCase();
            continueProgram = userResponse.equals("yes");
            scanner.nextLine(); // Clear buffer
            System.out.println();

        } while (continueProgram);

        scanner.close();
        System.out.println("Program terminated. Thank you for using the Customer Record Deletion program.");
    }
}
