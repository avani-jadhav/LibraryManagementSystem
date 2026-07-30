package connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MySqlConnection {

    private static Connection con = null;

    public static Connection getConnection() {

        try {

            // Load MySQL JDBC Driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Database URL
            String url = "jdbc:mysql://localhost:3306/library_management";

            // Database Username
            String username = "root";

            // Database Password
            String password = "Avani@1911";

            con = DriverManager.getConnection(url, username, password);

        } catch (ClassNotFoundException e) {
            System.out.println("MySQL Driver Not Found!");
            e.printStackTrace();
        } catch (SQLException e) {
            System.out.println("Database Connection Failed!");
            e.printStackTrace();
        }

        return con;
    }
}