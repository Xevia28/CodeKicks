package command.cart;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import command.Command;
import database.MySQL;
import entities.Cart;
import entities.Shoe;

public class AddToCartCommand implements Command {
    private Cart cart;
    private Shoe shoe;

    public AddToCartCommand(Cart cart, Shoe shoe) {
        this.cart = cart;
        this.shoe = shoe;
    }

    @Override
    public void execute() {
        cart.addProduct(shoe);
        saveCartActionToDatabase("add", shoe.getId());
        System.out.println("Shoe added to cart.");
    }

    private void saveCartActionToDatabase(String action, int productId) {
        MySQL db = MySQL.getInstance();
        Connection conn = db.getConnection();
        String query = "INSERT INTO cart_actions (action, product_id) VALUES (?, ?)";

        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, action);
            stmt.setInt(2, productId);
            stmt.executeUpdate();
            System.out.println("Cart action saved to database.");
        } catch (SQLException e) {
            System.out.println("Error saving cart action: " + e.getMessage());
        }
    }
}
