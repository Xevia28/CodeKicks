package services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import database.MySQL;
import entities.Shoe;
import framework.ShoeFactory;

public class ShoeService {
    private MySQL db;

    public ShoeService() {
        db = MySQL.getInstance();
    }

    public boolean createShoes(ShoeFactory factory, String type, String style, String size, double price) {
        Shoe shoe = factory.createShoe(type, style, size, price);
        return addShoeToDB(shoe);
    }

    private boolean addShoeToDB(Shoe shoe) {
        String query = "INSERT INTO shoes (type, brand, style, size, price) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = db.getConnection(); PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, shoe.getType());
            stmt.setString(2, shoe.getBrand());
            stmt.setString(3, shoe.getStyle());
            stmt.setString(4, shoe.getSize());
            stmt.setDouble(5, shoe.getPrice());
            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println("Error adding shoe to DB: " + e.getMessage());
            return false;
        }
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

    public boolean updateShoe(Shoe shoe) {
        String checkQuery = "SELECT 1 FROM shoes WHERE id = ?";
        String query = "UPDATE shoes SET type = ?, brand = ?, style = ?, size = ?, price = ? WHERE id = ?";
        try (Connection conn = db.getConnection();
                PreparedStatement stmt = conn.prepareStatement(query);
                PreparedStatement checkStmt = conn.prepareStatement(checkQuery)) {
            checkStmt.setInt(1, shoe.getId());
            try (ResultSet rs = checkStmt.executeQuery()) {
                if (!rs.next()) {
                    System.out.println("Shoe with id " + shoe.getId() + " does not exist in the cart!");
                    return false;
                }
            }
            stmt.setString(1, shoe.getType());
            stmt.setString(2, shoe.getBrand());
            stmt.setString(3, shoe.getStyle());
            stmt.setString(4, shoe.getSize());
            stmt.setDouble(5, shoe.getPrice());
            stmt.setInt(6, shoe.getId());
            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println("Error updating shoe: " + e.getMessage());
            return false;
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
