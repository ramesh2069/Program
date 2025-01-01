import java.sql.*;

public class StoredProcedureNoParameter {
    public static void main(String[] args) {

        String CONN_STRING = "jdbc:mysql://localhost:3306/learning";
        String user = "root";
        String password = "";

        try {

            Connection connection = DriverManager.getConnection(CONN_STRING, user, password);

            String query = "{CALL getUserDetails()}";

            CallableStatement callableStatement = connection.prepareCall(query);

            ResultSet rs = callableStatement.executeQuery();

            ResultSetMetaData meta = rs.getMetaData();

            for(int i = 1; i<=meta.getColumnCount(); i++){
                System.out.printf("%-15s", meta.getColumnName(i));
            }
            System.out.println();

            while (rs.next()){
                for(int i = 1; i<=meta.getColumnCount(); i++){
                    System.out.printf("%-15s", rs.getString(i));
                }
                System.out.println();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
