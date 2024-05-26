package factory.shoefactories;

import entities.Shoe;
import factory.ShoeFactory;

public class ConverseSF implements ShoeFactory {
    @Override
    public Shoe createShoe(String type, String style, String size, double price) {
        return new Shoe(type, "Converse", style, size, price);
    }
}
