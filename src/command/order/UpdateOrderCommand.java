package command.order;

import entities.Order;
import entities.User;
import framework.Command;
import framework.OrderState;
import services.OrderService;

public class UpdateOrderCommand implements Command {
    private OrderService orderService;
    private int orderId;
    private String newStatus;

    public UpdateOrderCommand(OrderService orderService, int orderId, String newStatus) {
        this.orderService = orderService;
        this.orderId = orderId;
        this.newStatus = newStatus;
    }

    @Override
    public void execute() {
        boolean success = orderService.updateOrderStatus(orderId, newStatus);
        if (success) {
            OrderState newState = orderService.getOrderState(newStatus);
            System.out.println("Order status updated successfully to " + newState.getStatus() + "!");
            Order order = orderService.getOrderById(orderId);
            order.setState(newState);
            User user = orderService.getUserByOrderId(orderId);
            order.addObserver(user);
            order.notifyObservers();
        } else {
            System.out.println("Failed to update order status.");
        }
    }
}
