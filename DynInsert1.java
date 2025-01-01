import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

class DynInsert {
    public static void main(String args[]) {
        String serverName ="172.16.20.20";
	String portNumber ="1521";
        String username = "c##mcaorc42";
        String password = "mcaorc42";
	String sid ="orcl";
	String url = "jdbc:oracle:thin:@//"+serverName+":"+portNumber+"/"+sid;
        Scanner sc = new Scanner(System.in);

        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            Connection conn = DriverManager.getConnection(url, username, password);
            System.out.println("Connection established successfully!!!");

            String query = "INSERT INTO customer(name, email, phone, account_creation_date) VALUES(?, ?, ?, ?)";
            PreparedStatement pstmt = conn.prepareStatement(query);

            System.out.println("How many records do you want to insert?");
            int recordCount = sc.nextInt();
            sc.nextLine();

            for (int i = 1; i <= recordCount; i++) {
                System.out.println("Enter details for record " + i + ":");

                System.out.print("name: ");
                String name = sc.nextLine();

                System.out.print("email: ");
                String email = sc.nextLine();

                System.out.print("phone: ");
                long phone = sc.nextLong();
                sc.nextLine();

                System.out.print("Account creation date (DD-MON-YY): ");
                String accountCreationDate = sc.nextLine();

                pstmt.setString(1, name);
                pstmt.setString(2, email);
                pstmt.setLong(3, phone);
                pstmt.setString(4, accountCreationDate);

                pstmt.executeUpdate();
                System.out.println("Record " + i + " inserted successfully!");
            }

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