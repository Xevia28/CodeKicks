package command.cart;

import java.util.List;

import entities.Cart;
import entities.Order;
import entities.Shoe;
import entities.User;
import framework.Command;
import framework.PaymentProcessor;
import services.CartService;
import services.OrderService;

public class CheckoutCommand implements Command {
    private Cart cart;
    private String address;
    private OrderService orderService;
    private CartService cartService;
    private double totalAmount;
    private PaymentProcessor paymentProcessor;

    public CheckoutCommand(Cart cart, String address, OrderService orderService, CartService cartService,
            PaymentProcessor paymentProcessor) {
        this.cart = cart;
        this.address = address;
        this.orderService = orderService;
        this.cartService = cartService;
        this.paymentProcessor = paymentProcessor;
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
            System.out.println("Cart is empty. Cannot proceed to checkout.");
            return;
        }

        totalAmount = calculateTotalAmount(shoes);

        if (!paymentProcessor.executePayment(totalAmount)) {
            System.out.println("Payment failed. Cannot proceed to checkout.");
            return;
        }

        int orderId = orderService.createOrder(cart.getUserId(), cart.getId(), address);
        if (orderId != -1) {
            System.out.println("Order created successfully. Order ID: " + orderId);
            Order order = orderService.getOrderById(orderId);
            User user = orderService.getUserByOrderId(orderId);
            order.addObserver(user);
            order.notifyOrderCreation();
            // cartService.clearCart(cart.getId());
        } else {
            System.out.println("Failed to create order.");
        }
    }

    private double calculateTotalAmount(List<Shoe> shoes) {
        double total = 0.0;
        for (Shoe shoe : shoes) {
            total += shoe.getPrice();
        }
        return total;
    }
}