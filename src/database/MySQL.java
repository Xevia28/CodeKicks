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

    // Private constructor to prevent instantiation from outside
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

    // Static method to get the singleton instance
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

    // Method to get the connection
    public Connection getConnection() {
        return conn;
    }

    // Method to close the connection
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
