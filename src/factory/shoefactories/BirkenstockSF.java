package factory.shoefactories;

import factory.shoes.Birkenstock;
import framework.ShoeFactory;

public class BirkenstockSF implements ShoeFactory {
    @Override
    public Birkenstock createShoe(String type, String style, String size, double price) {
        return new Birkenstock(type, style, size, price);
    }
}
