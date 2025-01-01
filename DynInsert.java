//javac -cp ".;mysql-connector-j-9.1.0.jar" DynInsert.java
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

class DynInsert {
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/mydatabase";
        String username = "root";
        String password = "";
        Scanner sc = new Scanner(System.in);

        System.out.println("==================================");
        System.out.println("       Dynamic Record Insertion   ");
        System.out.println("==================================");
        System.out.println("This program inserts customer records into the database.");
        System.out.println("Please follow the prompts to enter the details.");
        System.out.println();

        try {
            // Load MySQL JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Establish connection
            Connection conn = DriverManager.getConnection(url, username, password);
            System.out.println("Connection established successfully!");

            // SQL query for inserting records
            String query = "INSERT INTO customer(name, email, phone, account_creation_date) VALUES(?, ?, ?, ?)";
            PreparedStatement pstmt = conn.prepareStatement(query);

            // Validate the number of records to insert
            System.out.print("How many records do you want to insert? ");
            int recordCount = 0;
            while (true) {
                try {
                    recordCount = sc.nextInt();
                    if (recordCount <= 0) {
                        System.out.println("Please enter a positive integer.");
                    } else {
                        break;
                    }
                } catch (Exception e) {
                    System.out.println("Invalid input. Please enter a valid integer.");
                    sc.next(); // Clear invalid input
                }
            }
            sc.nextLine(); // Consume newline character

            // Insert records
            for (int i = 1; i <= recordCount; i++) {
                System.out.println("\nEnter details for record " + i + ":");

                System.out.print("Name: ");
                String name = sc.nextLine();

                System.out.print("Email: ");
                String email = sc.nextLine();

                long phone = 0;
                while (true) {
                    System.out.print("Phone: ");
                    try {
                        phone = sc.nextLong();
                        sc.nextLine(); // Consume newline character
                        break;
                    } catch (Exception e) {
                        System.out.println("Invalid input. Please enter a valid phone number.");
                        sc.next(); // Clear invalid input
                    }
                }

                System.out.print("Account creation date (YYYY-MM-DD): ");
                String accountCreationDate = sc.nextLine();

                // Set parameters for the prepared statement
                pstmt.setString(1, name);
                pstmt.setString(2, email);
                pstmt.setLong(3, phone);
                pstmt.setString(4, accountCreationDate);

                // Execute the query
                pstmt.executeUpdate();
                System.out.println("Record " + i + " inserted successfully!");
            }

            // Close the connection
            conn.close();
        } catch (ClassNotFoundException e) {
            System.err.println("JDBC Driver not found.");
            e.printStackTrace();
        } catch (SQLException e) {
            System.err.println("SQL ERROR: " + e.getMessage());
            e.printStackTrace();
        } finally {
            sc.close();
        }
    }
}
