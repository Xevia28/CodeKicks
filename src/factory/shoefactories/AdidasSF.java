package factory.shoefactories;

import factory.shoes.Adidas;
import framework.ShoeFactory;

public class AdidasSF implements ShoeFactory {
    @Override
    public Adidas createShoe(String type, String style, String size, double price) {
        return new Adidas(type, style, size, price);
    }
}
