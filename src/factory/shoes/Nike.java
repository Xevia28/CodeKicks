package factory.shoes;

import entities.Shoe;

public class Nike extends Shoe {
    Nike(String type, String style, String size, double price) {
        super(type, "Nike", style, size, price);
    }
}