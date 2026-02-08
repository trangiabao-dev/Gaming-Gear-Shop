package utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Computing Fundamental - HCM Campus
 */
public class dbutils {
//    Do not change this code
    private static final String DB_NAME = "GamingGearShop"; // ten table sql
    private static final String DB_USER_NAME = "sa"; // login sql
    private static final String DB_PASSWORD = "12345"; // password sql

    public static Connection getConnection() throws ClassNotFoundException, SQLException {
        Connection conn = null;
        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        String url = "jdbc:sqlserver://localhost:1433;databaseName=" + DB_NAME;
        conn = DriverManager.getConnection(url, DB_USER_NAME, DB_PASSWORD);
        return conn;
    }
    
    public static void main(String[] args) {
        try {
            System.out.println(getConnection());
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(dbutils.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
