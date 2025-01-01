import java.sql.*;

public class FetchCustomerData {
	public static void main(String[] args) {
    	String dbServer = "172.16.20.20"; // Server name
    	String dbPort = "1521";       	// Port number
    	String dbUsername = "c##mcaorc42"; // Database username
    	String dbPassword = "mcaorc42";	// Database password
    	String dbSid = "orcl";        	// Database SID
    	String dbUrl = "jdbc:oracle:thin:@//" + dbServer + ":" + dbPort + "/" + dbSid;

    	try {
        	Class.forName("oracle.jdbc.driver.OracleDriver");
        	Connection connection = DriverManager.getConnection(dbUrl, dbUsername, dbPassword);
        	System.out.println("Database connection established.");

        	String query = "SELECT * FROM customer";
        	PreparedStatement preparedStatement = connection.prepareStatement(query);
        	ResultSet resultSet = preparedStatement.executeQuery();

        	System.out.println("Customer Records:");
        	System.out.println("ID   Name      	Email              	Phone      	Account Created");

        	while (resultSet.next()) {
            	int id = resultSet.getInt(1);
            	String name = resultSet.getString(2);
            	String email = resultSet.getString(3);
            	String phone = resultSet.getString(4);
            	Date accountCreated = resultSet.getDate(5);

            	System.out.printf("%-4d %-12s %-20s %-12s %s%n", id, name, email, phone, accountCreated);
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
