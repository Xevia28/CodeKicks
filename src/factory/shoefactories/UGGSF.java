package factory.shoefactories;

import factory.shoes.UGG;
import framework.ShoeFactory;

public class UGGSF implements ShoeFactory {
    @Override
    public UGG createShoe(String type, String style, String size, double price) {
        return new UGG(type, style, size, price);
    }
}
