package state;

import entities.Order;

public interface OrderState {
    void next(Order order);

    void prev(Order order);

    void printStatus();

    String getStatus();
}
