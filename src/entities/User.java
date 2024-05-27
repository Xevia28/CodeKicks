package entities;

import observer.Observer;

public class User implements Observer {
    private int id;
    private String name;
    private String email;
    private String password;
    private boolean isAdmin;

    public User(int id, String name, String email, String password, boolean isAdmin) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.isAdmin = isAdmin;
    }

    public User(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public boolean isAdmin() {
        return isAdmin;
    }

    @Override
    public void notify(Order order) {
        System.out.println("Hello " + name + ", your order has been successfully created with ID: " + order.getId());
    }

    @Override
    public void update(Order order) {
        System.out.println("Hello " + name + ", your order status has been updated to: " + order.getOrderStatus());
    }
}
