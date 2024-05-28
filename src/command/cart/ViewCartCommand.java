package command.cart;

import entities.Cart;
import entities.Shoe;
import framework.Command;
import services.CartService;

import java.util.List;

public class ViewCartCommand implements Command {
    private CartService cartService;
    private Cart cart;

    public ViewCartCommand(CartService cartService, Cart cart) {
        this.cartService = cartService;
        this.cart = cart;
    }

    @Override
    public void execute() {
        int cartId = cart.getId();
        if (cartId == 0) {
            System.out.println("Cart is empty!");
            return;
        }
        List<Shoe> shoes = cartService.getCartItems(cartId);
        if (shoes.isEmpty()) {
            System.out.println("Cart is empty!");
        } else {
            System.out.println("Cart Items:");
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
}
