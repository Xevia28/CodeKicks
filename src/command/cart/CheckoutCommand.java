package command.cart;

public class CheckoutCommand implements Command {
    private Cart cart;
    private OrderService orderService;

    public CheckoutCommand(Cart cart, OrderService orderService) {
        this.cart = cart;
        this.orderService = orderService;
    }

    @Override
    public void execute() {
        if (cart.isEmpty()) {
            System.out.println("Cart is empty. Cannot proceed to checkout.");
            return;
        }

        Order order = orderService.createOrder(cart);
        System.out.println("Order created with ID: " + order.getId());
        order.printStatus();

        // Simulate processing the order
        order.nextState();
        order.printStatus();

        // Simulate shipping the order
        order.nextState();
        order.printStatus();

        // Simulate delivering the order
        order.nextState();
        order.printStatus();
    }
}