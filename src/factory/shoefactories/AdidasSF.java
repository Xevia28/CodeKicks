package factory.shoefactories;

import entities.Shoe;
import factory.ShoeFactory;

public class AdidasSF implements ShoeFactory {
    @Override
    public Shoe createShoe(String type, String style, String size, double price) {
        return new Shoe(type, "Adidas", style, size, price);
    }
}
