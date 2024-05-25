package entities;

import java.util.ArrayList;
import java.util.List;

public class Cart {
    private List<Shoe> shoes;

    public Cart() {
        shoes = new ArrayList<Shoe>();
    }

    public void addProduct(Shoe shoe) {
        shoes.add(shoe);
    }

    public void getCartItems() {
        int c = 0;
        for (Shoe shoe : shoes) {
            c++;
            System.out.println("Cart item " + c + ": " + shoe.getBrand() + " shoes for " + shoe.getType());
        }
    }
}
