package services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import database.MySQL;
import entities.Order;
import entities.Shoe;
import entities.User;
import framework.OrderState;
import state.DeliveredState;
import state.ProcessingState;
import state.ShippedState;

public class OrderService {
    private MySQL db;

    public OrderService() {
        db = MySQL.getInstance();
    }

    public int createOrder(int userId, int cartId, String address) {
        String orderQuery = "INSERT INTO orders (user_id, cart_id, address, status) VALUES (?, ?, ?, ?)";
        try (Connection conn = db.getConnection();
                PreparedStatement orderStmt = conn.prepareStatement(orderQuery, Statement.RETURN_GENERATED_KEYS)) {
            conn.setAutoCommit(false);
            orderStmt.setInt(1, userId);
            orderStmt.setInt(2, cartId);
            orderStmt.setString(3, address);
            orderStmt.setString(4, "Processing");
            orderStmt.executeUpdate();
            int orderId;
            try (ResultSet generatedKeys = orderStmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    orderId = generatedKeys.getInt(1);
                } else {
                    conn.rollback();
                    throw new SQLException("Creating order failed, no ID obtained.");
                }
            }
            conn.commit();
            return orderId;
        } catch (SQLException e) {
            System.out.println("Error creating order: " + e.getMessage());
            return -1;
        }
    }

    public List<Order> getOrders(int userId) {
        String orderQuery = "SELECT o.id, o.address, o.cart_id, o.status FROM orders o WHERE o.user_id = ?";
        String itemsQuery = "SELECT s.id, s.type, s.brand, s.style, s.size, s.price " +
                "FROM shoes s " +
                "JOIN cart_items ci ON s.id = ci.shoe_id " +
                "WHERE ci.cart_id = ?";
        List<Order> orders = new ArrayList<>();
        try (Connection conn = db.getConnection();
                PreparedStatement orderStmt = conn.prepareStatement(orderQuery);
                PreparedStatement itemsStmt = conn.prepareStatement(itemsQuery)) {
            orderStmt.setInt(1, userId);
            ResultSet orderRs = orderStmt.executeQuery();
            while (orderRs.next()) {
                int orderId = orderRs.getInt("id");
                String address = orderRs.getString("address");
                int cartId = orderRs.getInt("cart_id");
                String status = orderRs.getString("status");
                itemsStmt.setInt(1, cartId);
                ResultSet itemsRs = itemsStmt.executeQuery();
                List<Shoe> shoes = new ArrayList<>();
                while (itemsRs.next()) {
                    Shoe shoe = new Shoe(
                            itemsRs.getInt("id"),
                            itemsRs.getString("type"),
                            itemsRs.getString("brand"),
                            itemsRs.getString("style"),
                            itemsRs.getString("size"),
                            itemsRs.getDouble("price"));
                    shoes.add(shoe);
                }
                Order order = new Order(orderId, userId, cartId, address, shoes, status);
                orders.add(order);
            }
            if (orders.isEmpty()) {
                System.out.println("No orders found for user ID: " + userId);
            }
        } catch (SQLException e) {
            System.out.println("Error getting orders: " + e.getMessage());
        }
        return orders;
    }

    public boolean updateOrderStatus(int orderId, String newStatus) {
        String checkQuery = "SELECT status FROM orders WHERE id = ?";
        String query = "UPDATE orders SET status = ? WHERE id = ?";
        try (Connection conn = db.getConnection();
                PreparedStatement stmt = conn.prepareStatement(query);
                PreparedStatement checkStmt = conn.prepareStatement(checkQuery)) {
            checkStmt.setInt(1, orderId);
            ResultSet rs = checkStmt.executeQuery();

            if (!rs.next()) {
                System.out.println("Order with id " + orderId + " does not exist!");
                return false;
            }
            String currentStatus = rs.getString("status");
            if (currentStatus.equals("Shipped") && newStatus.equals("Shipped")) {
                System.out.println("Order is already shipped!");
                return false;
            }
            if (currentStatus.equals("Delivered") && (newStatus.equals("Shipped") || newStatus.equals("Delivered"))) {
                System.out.println("Order is already delivered!");
                return false;
            }
            stmt.setString(1, newStatus);
            stmt.setInt(2, orderId);
            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println("Error updating order status: " + e.getMessage());
            return false;
        }
    }

    public OrderState getOrderState(String status) {
        switch (status.toLowerCase()) {
            case "shipped":
                return new ShippedState();
            case "delivered":
                return new DeliveredState();
            default:
                return new ProcessingState();
        }
    }

    public Order getOrderById(int orderID) {
        String query = "SELECT * FROM orders WHERE id = ?";
        try (Connection conn = db.getConnection(); PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, orderID);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    Order order = new Order(
                            rs.getInt("id"),
                            rs.getInt("user_id"),
                            rs.getInt("cart_id"),
                            rs.getString("address"));
                    return order;
                } else {
                    return null;
                }
            }
        } catch (SQLException e) {
            System.out.println("Error getting order: " + e.getMessage());
            return null;
        }

    }

    public User getUserByOrderId(int orderID) {
        String orderQuery = "SELECT user_id FROM orders WHERE id = ?";
        String userQuery = "SELECT * FROM users WHERE id = ?";

        try (Connection conn = db.getConnection();
                PreparedStatement orderStmt = conn.prepareStatement(orderQuery)) {
            orderStmt.setInt(1, orderID);
            try (ResultSet orderRs = orderStmt.executeQuery()) {
                if (orderRs.next()) {
                    int userId = orderRs.getInt("user_id");
                    try (PreparedStatement userStmt = conn.prepareStatement(userQuery)) {
                        userStmt.setInt(1, userId);
                        try (ResultSet userRs = userStmt.executeQuery()) {
                            if (userRs.next()) {
                                User user = new User(
                                        userRs.getInt("id"),
                                        userRs.getString("name"),
                                        userRs.getString("email"),
                                        userRs.getString("password"),
                                        userRs.getBoolean("isAdmin"));
                                return user;
                            } else {
                                System.out.println("User not found for user ID: " + userId);
                                return null;
                            }
                        }
                    }
                } else {
                    System.out.println("Order not found for order ID: " + orderID);
                    return null;
                }
            }
        } catch (SQLException e) {
            System.out.println("Error getting user by order ID: " + e.getMessage());
            return null;
        }
    }
}
