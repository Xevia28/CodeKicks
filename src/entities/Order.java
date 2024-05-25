package entities;

import state.OrderState;
import state.ProcessingState;

public class Order {
    private int id;
    private int userid;
    private int cartid;
    private OrderState state;

    public Order(int id, int userid, int cartid) {
        this.id = id;
        this.userid = userid;
        this.cartid = cartid;
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

    public int getUserid() {
        return userid;
    }

    public int getCartid() {
        return cartid;
    }
}
