package factory.shoes;

import entities.Shoe;

public class Adidas extends Shoe {
    Adidas(String type, String style, String size, double price) {
        super(type, "Adidas", style, size, price);
    }
}
