package command.cart;

import java.util.List;

import command.Command;
import entities.Cart;
import entities.Shoe;
import services.CartService;

public class RemoveFromCartCommand implements Command {
    private CartService cartService;
    private Cart cart;
    private int shoeId;

    public RemoveFromCartCommand(CartService cartService, Cart cart, int shoeId) {
        this.cartService = cartService;
        this.cart = cart;
        this.shoeId = shoeId;
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
            if (cartService.removeFromCart(cartId, shoeId)) {
                System.out.println("Shoe removed from cart.");
            } else {
                System.out.println("Failed to remove shoe from cart.");
            }
        }

    }

}
