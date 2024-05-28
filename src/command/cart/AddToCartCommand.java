package command.cart;

import entities.Cart;
import framework.Command;
import services.CartService;

public class AddToCartCommand implements Command {
    private CartService cartService;
    private Cart cart;
    private int shoeId;

    public AddToCartCommand(CartService cartService, Cart cart, int shoeId) {
        this.cartService = cartService;
        this.cart = cart;
        this.shoeId = shoeId;
    }

    @Override
    public void execute() {
        int cartId = cart.getId();
        if (cartId == 0) {
            cartId = cartService.createCart(cart.getUserId());
            cart.setId(cartId);
        }
        if (cartService.addToCart(cartId, shoeId)) {
            System.out.println("Shoe added to cart.");
        } else {
            System.out.println("Failed to add shoe to cart.");
        }
    }
}
