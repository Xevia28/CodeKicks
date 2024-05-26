package state;

import entities.Order;

public class ProcessingState implements OrderState {
    @Override
    public void next(Order order) {
        order.setState(new ShippedState());
    }

    @Override
    public void prev(Order order) {
        System.out.println("The order is in its initial state.");
    }

    @Override
    public void printStatus() {
        System.out.println("Order is being processed.");
    }

    @Override
    public String getStatus() {
        return "Processing";
    }
}
