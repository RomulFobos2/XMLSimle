package org.example;

import java.io.FileReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

public class ManageDB {
    private static Connection currentConnection;

    public static void connectToDB() throws Exception {
        Properties properties = new Properties();
        properties.load(new FileReader("src\\main\\resources\\setting.properties"));
        String url = properties.getProperty("url");
        String username = properties.getProperty("username");
        String password = properties.getProperty("password");
        Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance();
        currentConnection = DriverManager.getConnection(url, username, password);
    }

    public static Connection getCurrentConnection() {
        return currentConnection;
    }
}
