package factory.shoefactories;

import factory.shoes.Timberland;
import framework.ShoeFactory;

public class TimberlandSF implements ShoeFactory {
    @Override
    public Timberland createShoe(String type, String style, String size, double price) {
        return new Timberland(type, style, size, price);
    }
}
