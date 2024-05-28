package factory.shoefactories;

import factory.shoes.Nike;
import framework.ShoeFactory;

public class NikeSF implements ShoeFactory {
    @Override
    public Nike createShoe(String type, String style, String size, double price) {
        return new Nike(type, style, size, price);
    }
}
