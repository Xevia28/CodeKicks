package services;

import database.MySQL;

public class OrderService {
    private MySQL db;

    public OrderService() {
        db = MySQL.getInstance();
    }

}
