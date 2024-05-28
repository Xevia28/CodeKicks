package factory.shoefactories;

import factory.shoes.Converse;
import framework.ShoeFactory;

public class ConverseSF implements ShoeFactory {
    @Override
    public Converse createShoe(String type, String style, String size, double price) {
        return new Converse(type, style, size, price);
    }
}
