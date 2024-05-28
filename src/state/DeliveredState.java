package state;

import entities.Order;
import framework.OrderState;

public class DeliveredState implements OrderState {
    @Override
    public void next(Order order) {
        System.out.println("The order is already delivered.");
    }

    @Override
    public void prev(Order order) {
        order.setState(new ShippedState());
    }

    @Override
    public void printStatus() {
        System.out.println("Order is delivered.");
    }

    @Override
    public String getStatus() {
        return "Delivered";
    }
}
