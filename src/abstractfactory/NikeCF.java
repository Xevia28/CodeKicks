package abstractfactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import database.MySQL;

public class NikeCF implements ShoeComponentFactory {
    @Override
    public String createSize() {
        String size = "10";
        saveComponentToDatabase("size", size);
        return size;
    }

    @Override
    public String createColor() {
        String color = "Red";
        saveComponentToDatabase("color", color);
        return color;
    }

    @Override
    public String createMaterial() {
        String material = "Leather";
        saveComponentToDatabase("material", material);
        return material;
    }

    private void saveComponentToDatabase(String type, String value) {
        MySQL db = MySQL.getInstance();
        Connection conn = db.getConnection();
        String query = "INSERT INTO shoe_components (type, value) VALUES (?, ?)";

        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, type);
            stmt.setString(2, value);
            stmt.executeUpdate();
            System.out.println(type + " saved to database.");
        } catch (SQLException e) {
            System.out.println("Error saving " + type + " to database: " + e.getMessage());
        }
    }
}