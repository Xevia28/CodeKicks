package entities;

import java.util.ArrayList;
import java.util.List;

import framework.Observer;
import framework.OrderState;
import state.DeliveredState;
import state.ProcessingState;
import state.ShippedState;

public class Order {
    private int id;
    private int userid;
    private int cartid;
    private String address;
    private List<Shoe> shoes;
    // private String status;

    private List<Observer> observers;
    private OrderState state;

    public Order(int id, int userid, int cartid, String address) {
        this.id = id;
        this.userid = userid;
        this.cartid = cartid;
        this.address = address;
        // this.status = "Processing";
        state = new ProcessingState();
        observers = new ArrayList<Observer>();
    }

    public Order(int id, int userid, int cartid, String address, List<Shoe> shoes, String status) {
        this.id = id;
        this.userid = userid;
        this.cartid = cartid;
        this.address = address;
        this.shoes = shoes;
        // this.status = status;
        state = status.equals("Processing") ? new ProcessingState()
                : (status.equals("Shipped") ? new ShippedState() : new DeliveredState());
        observers = new ArrayList<Observer>();
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

    public void addObserver(Observer observer) {
        observers.add(observer);
    }

    public void notifyOrderCreation() {
        for (Observer observer : observers) {
            observer.notify(this);
        }
    }

    public void notifyObservers() {
        for (Observer observer : observers) {
            observer.update(this);
        }
    }

    public void printStatus() {
        state.printStatus();
    }

    public String getOrderStatus() {
        return state.getStatus();
    }

    public void setAddress(String address) {
        this.address = address;
    }

    // public void setStatus(String status) {
    // this.status = status;
    // }

    public int getId() {
        return id;
    }

    public int getUserid() {
        return userid;
    }

    public int getCartid() {
        return cartid;
    }

    public String getAddress() {
        return address;
    }

    // public String getStatus() {
    // return status;
    // }

    public List<Shoe> getShoes() {
        return shoes;
    }

    public void printOrderDetails() {
        System.out.println("Order ID: " + id);
        System.out.println("User ID: " + userid);
        System.out.println("Address: " + address);
        System.out.print("Order Status: ");
        printStatus();
        System.out.println("Shoes ordered:");
        for (Shoe shoe : shoes) {
            System.out.println("    Shoe ID: " + shoe.getId());
            System.out.println("        Type: " + shoe.getType());
            System.out.println("        Brand: " + shoe.getBrand());
            System.out.println("        Style: " + shoe.getStyle());
            System.out.println("        Size: " + shoe.getSize());
            System.out.println("        Price: Nu." + shoe.getPrice());
        }
    }
}
