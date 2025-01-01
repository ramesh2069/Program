import java.sql.*;

public class FetchCustomerData {
    public static void main(String[] args) {
        String dbUrl = "jdbc:mysql://localhost:3306/mydatabase"; // Database URL
        String dbUsername = "root";                             // Database username
        String dbPassword = "";                                 // Database password

        try {
            // Load MySQL JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection(dbUrl, dbUsername, dbPassword);
            System.out.println("Database connection established.");

            String query = "SELECT * FROM customer";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();

            System.out.println("Customer Records:");
            System.out.println("ID   Name           Email                   Phone         Account Created");

            while (resultSet.next()) {
                int id = resultSet.getInt("id"); // Use column names for clarity
                String name = resultSet.getString("name");
                String email = resultSet.getString("email");
                String phone = resultSet.getString("phone");
                Date accountCreated = resultSet.getDate("account_creation_date");

                System.out.printf("%-4d %-14s %-22s %-13s %s%n", id, name, email, phone, accountCreated);
            }

            resultSet.close();
            preparedStatement.close();
            connection.close();
        } catch (ClassNotFoundException e) {
            System.out.println("JDBC Driver not found. Please check your configuration.");
            e.printStackTrace();
        } catch (SQLException e) {
            System.out.println("Database error: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
