package factory.shoes;

import entities.Shoe;

public class Converse extends Shoe {
    Converse(String type, String style, String size, double price) {
        super(type, "Converse", style, size, price);
    }
}