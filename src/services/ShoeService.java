package services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import database.MySQL;
import entities.Shoe;

public class ShoeService {
    private MySQL db;

    public ShoeService() {
        db = MySQL.getInstance();
    }

    public List<Shoe> getShoes() {
        List<Shoe> shoes = new ArrayList<>();
        String query = "SELECT * FROM shoes";
        try (Connection conn = db.getConnection(); PreparedStatement stmt = conn.prepareStatement(query)) {
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Shoe shoe = new Shoe(
                        rs.getInt("id"),
                        rs.getString("type"),
                        rs.getString("brand"),
                        rs.getString("style"),
                        rs.getString("size"),
                        rs.getDouble("price"));
                shoes.add(shoe);
            }
        } catch (SQLException e) {
            System.out.println("Error retrieving shoes: " + e.getMessage());
        }
        return shoes;
    }

    public Shoe getShoe(int shoeId) {
        String query = "SELECT * FROM shoes WHERE id = ?";
        try (Connection conn = db.getConnection(); PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, shoeId);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    Shoe shoe = new Shoe(
                            rs.getInt("id"),
                            rs.getString("type"),
                            rs.getString("brand"),
                            rs.getString("style"),
                            rs.getString("size"),
                            rs.getDouble("price"));
                    return shoe;
                } else {
                    return null; // No shoe found with the given ID
                }
            }
        } catch (SQLException e) {
            System.out.println("Error getting shoe: " + e.getMessage());
            return null;
        }
    }

    public boolean deleteShoe(int shoeId) {
        String query = "DELETE FROM shoes WHERE id = ?";
        try (Connection conn = db.getConnection(); PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, shoeId);
            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            System.out.println("Error deleting shoe: " + e.getMessage());
            return false;
        }
    }
}
