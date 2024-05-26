package factory.shoes;

import entities.Shoe;

public class Merrell extends Shoe {
    Merrell(String type, String style, String size, double price) {
        super(type, "Merrell", style, size, price);
    }
}