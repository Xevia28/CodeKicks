package observer;

import entities.Order;

public interface Observer {
    void notify(Order order);

    void update(Order order);
}
