package state;

import entities.Order;
import framework.OrderState;

public class ShippedState implements OrderState {
    @Override
    public void next(Order order) {
        order.setState(new DeliveredState());
    }

    @Override
    public void prev(Order order) {
        order.setState(new ProcessingState());
    }

    @Override
    public void printStatus() {
        System.out.println("Order is shipped.");
    }

    @Override
    public String getStatus() {
        return "Shipped";
    }
}
