package util;

import java.sql.*;


public class DatabaseConnection {
    private static String url = "jdbc:mysql://localhost/test1";
    private static String username = "root";
    private static String password = "root";

    public static Connection getConnection() throws SQLException {
        Connection connection = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(url, username, password);
        }  catch (SQLException ex) {
            System.out.print(ex.toString());
            System.exit(0);
        } catch (ClassNotFoundException ex) {
            System.out.print(ex.toString());
        }
        return connection;
    }
}
