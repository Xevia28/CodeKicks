package factory.shoes;

import entities.Shoe;

public class JM extends Shoe {
    public JM(String type, String style, String size, double price) {
        super(type, "Johnston & Murphy", style, size, price);
    }
}