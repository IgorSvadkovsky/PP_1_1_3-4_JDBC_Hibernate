package jm.task.core.jdbc.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util {
    private String url;
    private String username;
    private String password;

    public Util(String url, String username, String password) {
        this.url = url;
        this.username = username;
        this.password = password;
    }

    public Connection connectToDb() throws SQLException {
        return DriverManager.getConnection(url, username, password);
    }
}
