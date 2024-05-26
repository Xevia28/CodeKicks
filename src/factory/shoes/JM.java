package factory.shoes;

import entities.Shoe;

public class JM extends Shoe {
    JM(String type, String style, String size, double price) {
        super(type, "Johnston & Murphy", style, size, price);
    }
}