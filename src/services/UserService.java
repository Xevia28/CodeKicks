package services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import database.MySQL;
import entities.User;

public class UserService {
    private MySQL db;

    public UserService() {
        db = MySQL.getInstance();
    }

    public boolean signUp(User user) {
        String query = "INSERT INTO users (name, email, password) VALUES (?, ?, ?)";
        try (Connection conn = db.getConnection(); PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, user.getName());
            stmt.setString(2, user.getEmail());
            stmt.setString(3, user.getPassword());
            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println("Error signing up: " + e.getMessage());
            return false;
        }
    }

    public String login(User user) {
        String query = "SELECT * FROM users WHERE email = ? AND password = ?";
        try (Connection conn = db.getConnection(); PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, user.getEmail());
            stmt.setString(2, user.getPassword());
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                if (rs.getBoolean("isAdmin")) {
                    return "admin";
                } else {
                    return "user";
                }
            } else {
                return "fail";
            }
        } catch (SQLException e) {
            System.out.println("Error logging in: " + e.getMessage());
            return "fail";
        }
    }
}
