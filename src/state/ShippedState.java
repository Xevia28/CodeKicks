package state;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import database.MySQL;
import entities.Order;

public class ShippedState implements OrderState {
    @Override
    public void next(Order order) {
        order.setState(new DeliveredState());
        updateOrderStatusInDatabase(order.getId(), "Delivered");
    }

    @Override
    public void prev(Order order) {
        order.setState(new ProcessingState());
    }

    @Override
    public void printStatus() {
        System.out.println("Order is shipped.");
    }

    private void updateOrderStatusInDatabase(int orderId, String status) {
        MySQL db = MySQL.getInstance();
        Connection conn = db.getConnection();
        String query = "UPDATE orders SET status = ? WHERE id = ?";

        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, status);
            stmt.setInt(2, orderId);
            stmt.executeUpdate();
            System.out.println("Order status updated to " + status + " in database.");
        } catch (SQLException e) {
            System.out.println("Error updating order status: " + e.getMessage());
        }
    }
}
