package factory;

import entities.Shoe;

public class AdidasSF extends ShoeFactory {
    @Override
    public Shoe createShoe() {
        return new Shoe("Adidas", "Fashion", "10");
    }
}
