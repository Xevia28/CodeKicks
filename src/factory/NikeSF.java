package factory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import database.MySQL;
import entities.Shoe;

public class NikeSF extends ShoeFactory {
    @Override
    public Shoe createShoe() {
        Shoe shoe = new Shoe("Nike", "Running", "10");
        saveShoeToDatabase(shoe);
        return shoe;
    }

    private void saveShoeToDatabase(Shoe shoe) {
        MySQL db = MySQL.getInstance();
        Connection conn = db.getConnection();
        String query = "INSERT INTO shoes (brand, style, size) VALUES (?, ?, ?)";

        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, shoe.getBrand());
            stmt.setString(2, shoe.getStyle());
            stmt.setString(3, shoe.getSize());
            stmt.executeUpdate();
            System.out.println("Shoe saved to database.");
        } catch (SQLException e) {
            System.out.println("Error saving shoe to database: " + e.getMessage());
        }
    }
}
