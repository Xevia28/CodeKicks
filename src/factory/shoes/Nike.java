package factory.shoes;

import entities.Shoe;

public class Nike extends Shoe {
    public Nike(String type, String style, String size, double price) {
        super(type, "Nike", style, size, price);
    }
}