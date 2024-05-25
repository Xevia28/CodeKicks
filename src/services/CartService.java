package services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import database.MySQL;
import entities.Shoe;

public class CartService {
    private MySQL db;

    public CartService() {
        db = MySQL.getInstance();
    }

    public int createCart(int userId) {
        String query = "INSERT INTO carts (user_id) VALUES (?)";
        try (Connection conn = db.getConnection();
                PreparedStatement stmt = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setInt(1, userId);
            stmt.executeUpdate();
            try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    return generatedKeys.getInt(1);
                } else {
                    throw new SQLException("Creating cart failed, no ID obtained.");
                }
            }
        } catch (SQLException e) {
            System.out.println("Error creating cart: " + e.getMessage());
            return -1;
        }
    }

    public boolean addToCart(int cartId, int shoeId) {
        String query = "INSERT INTO cart_items (cart_id, shoe_id) VALUES (?, ?)";
        try (Connection conn = db.getConnection(); PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, cartId);
            stmt.setInt(2, shoeId);
            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            if (e.getErrorCode() == 1452) {
                System.out.println("Shoe with id " + shoeId + " does not exist!");
                return false;
            }
            System.out.println("Error adding to cart: " + e.getMessage());
            return false;
        }
    }

    public List<Shoe> getCartItems(int cartId) {
        String query = "SELECT s.id, s.type, s.brand, s.style, s.size, s.price FROM shoes s JOIN cart_items ci ON s.id = ci.shoe_id WHERE ci.cart_id = ?";
        List<Shoe> shoes = new ArrayList<>();
        try (Connection conn = db.getConnection(); PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, cartId);
            try (ResultSet rs = stmt.executeQuery()) {
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
            }
        } catch (SQLException e) {
            System.out.println("Error getting cart items: " + e.getMessage());
        }
        return shoes;
    }

    public boolean removeFromCart(int cartId, int shoeId) {
        String query = "DELETE FROM cart_items WHERE cart_id = ? AND shoe_id = ?";
        try (Connection conn = db.getConnection(); PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, cartId);
            stmt.setInt(2, shoeId);
            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println("Error removing from cart: " + e.getMessage());
            return false;
        }
    }

    public boolean clearCart(int cartId) {
        String query = "DELETE FROM cart_items WHERE cart_id = ?";
        try (Connection conn = db.getConnection(); PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, cartId);
            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println("Error clearing cart: " + e.getMessage());
            return false;
        }
    }
}
