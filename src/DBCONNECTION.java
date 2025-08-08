import java.sql.*;

public class DBCONNECTION {
    public static Connection getConnection() {
        Connection conn = null;
        try {
            String url = "jdbc:mysql://localhost:3306/student_db";
            String user = "root";
            String pass = "Karthi@2006"; // 🔒 Change this
            conn = DriverManager.getConnection(url, user, pass);
        } catch (Exception e) {
            System.out.println("DB Error: " + e);
        }
        return conn;
    }
}
