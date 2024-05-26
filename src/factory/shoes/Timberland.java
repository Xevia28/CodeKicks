package factory.shoes;

import entities.Shoe;

public class Timberland extends Shoe {
    Timberland(String type, String style, String size, double price) {
        super(type, "Timberland", style, size, price);
    }
}