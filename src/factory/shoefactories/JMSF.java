package factory.shoefactories;

import factory.shoes.JM;
import framework.ShoeFactory;

public class JMSF implements ShoeFactory {
    @Override
    public JM createShoe(String type, String style, String size, double price) {
        return new JM(type, style, size, price);
    }
}
