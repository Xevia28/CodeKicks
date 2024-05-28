package factory.shoefactories;

import factory.shoes.Merrell;
import framework.ShoeFactory;

public class MerrellSF implements ShoeFactory {
    @Override
    public Merrell createShoe(String type, String style, String size, double price) {
        return new Merrell(type, style, size, price);
    }
}
