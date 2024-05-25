package entities;

import state.OrderState;
import state.ProcessingState;

public class Order {
    private int id;
    private OrderState state;

    public Order() {
        state = new ProcessingState();
    }

    public void process() {

    }

    public void setState(OrderState state) {
        this.state = state;
    }

    public void nextState() {
        state.next(this);
    }

    public void prevState() {
        state.prev(this);
    }

    public void printStatus() {
        state.printStatus();
    }

    public int getId() {
        return id;
    }
}
