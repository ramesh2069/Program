import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

class DynUpdate {
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

            String query = "UPDATE customer SET email = ?, phone = ? WHERE name = ?";
            PreparedStatement pstmt = conn.prepareStatement(query);

            System.out.print("Enter the name of the customer to update: ");
            String name = sc.nextLine();

            System.out.print("Enter new email: ");
            String email = sc.nextLine();

            System.out.print("Enter new phone: ");
            long phone = sc.nextLong();

            pstmt.setString(1, email);
            pstmt.setLong(2, phone);
            pstmt.setString(3, name);

            int rowsUpdated = pstmt.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("Record updated successfully!");
            } else {
                System.out.println("No record found with the given name.");
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