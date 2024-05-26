package factory;

import entities.Shoe;

public interface ShoeFactory {
    Shoe createShoe(String type, String style, String size, double price);
}
