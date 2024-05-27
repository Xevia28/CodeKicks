package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MySQL {
    private static MySQL instance;
    private Connection conn;
    private static final String URL = "jdbc:mysql://localhost:3306/codekicks";
    private static final String USER = "root";
    private static final String PASS = "root";

    private MySQL() {
        try {
            conn = DriverManager.getConnection(URL, USER, PASS);
            if (conn != null) {
                System.out.println("Database connected");
            }
        } catch (SQLException e) {
            System.out.println("Failed to connect to database: " + e.getMessage());
        }
    }

    public static MySQL getInstance() {
        if (instance == null) {
            synchronized (MySQL.class) {
                if (instance == null) {
                    instance = new MySQL();
                }
            }
        }
        return instance;
    }

    public Connection getConnection() {
        try {
            if (conn == null || conn.isClosed()) {
                conn = DriverManager.getConnection(URL, USER, PASS);
            }
        } catch (SQLException e) {
            System.out.println("Failed to reconnect to database: " + e.getMessage());
        }
        return conn;
    }

    public void closeConnection() {
        try {
            if (conn != null && !conn.isClosed()) {
                conn.close();
                System.out.println("Database connection closed");
            }
        } catch (SQLException e) {
            System.out.println("Error closing database connection: " + e.getMessage());
        }
    }
}
