package factory.shoefactories;

import factory.shoes.AE;
import framework.ShoeFactory;

public class AESF implements ShoeFactory {
    @Override
    public AE createShoe(String type, String style, String size, double price) {
        return new AE(type, style, size, price);
    }
}
