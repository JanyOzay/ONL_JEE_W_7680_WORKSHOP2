package pl.coderslab;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbUtil {

    private static final String URL = "jdbc:mysql://localhost:3306/workshop2?useSSL=false&characterEncoding=utf8";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "coderslab";

    private DbUtil() {
    }

    public static Connection connect() throws SQLException {
        return DriverManager.getConnection(URL, USERNAME, PASSWORD);
    }
}
