package entities;

import java.util.ArrayList;
import java.util.List;

public class Cart {
    private int id;
    private int userid;
    private List<Shoe> shoes;

    public Cart(int userid) {
        this.userid = userid;
        this.shoes = new ArrayList<>();
    }

    public Cart(int id, int userid, List<Shoe> shoes) {
        this.id = id;
        this.userid = userid;
        this.shoes = shoes;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public int getUserId() {
        return userid;
    }

    public List<Shoe> getShoes() {
        return shoes;
    }

    public void addShoe(Shoe shoe) {
        shoes.add(shoe);
    }

    public void removeShoe(Shoe shoe) {
        shoes.remove(shoe);
    }

    public void clearCart() {
        shoes.clear();
    }

    public boolean isEmpty() {
        return shoes.size() == 0;
    }

    // public void addProduct(Shoe shoe) {
    // shoes.add(shoe);
    // }

    public void getCartItems() {
        if (shoes.size() == 0) {
            System.out.println("Cart is empty!");
            return;
        }
        System.out.println("Cart items:");
        for (Shoe shoe : shoes) {
            System.out.println("    Shoe ID " + shoe.getId());
            System.out.println("        Type: " + shoe.getType());
            System.out.println("        Brand: " + shoe.getBrand());
            System.out.println("        Style: " + shoe.getStyle());
            System.out.println("        Size: " + shoe.getSize());
            System.out.println("        Price: Nu." + shoe.getPrice());
        }
    }
}
