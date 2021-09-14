package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConfig {
    public static String mobt = "postgresql";
    public static String host = "localhost";
    public static String port = "5432";
    public static String DB = "bootcamp_g11";
    public static String URL = "jdbc:" + mobt + "://" + host + ":" + port + "/" + DB;

    public static String user = "postgres";
    public static String password = "root123";


    public static Connection getConnection() throws SQLException, ClassNotFoundException {
        Class.forName("org.postgresql.Driver");
        Connection connection = DriverManager.getConnection(URL, user, password);
        return connection;
    }


}
