package jm.task.core.jdbc.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util {

    public static Connection connectToDb() throws SQLException {
        return DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/problem_1_1_3",
                "root",
                "root");
    }
}
