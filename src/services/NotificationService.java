package services;

import java.util.ArrayList;
import java.util.List;

import observer.Observer;
import observer.Subject;

public class NotificationService implements Subject {
    private List<Observer> observers;

    public NotificationService() {
        observers = new ArrayList<>();
    }

    @Override
    public void addObserver(Observer observer) {
        observers.add(observer);
    }

    @Override
    public void removeObserver(Observer observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyObservers(String message) {
        for (Observer observer : observers) {
            observer.update(message);
        }
    }

    public void orderStatusUpdate(String status) {
        notifyObservers("Order status updated to: " + status);
    }

    public void newPromotion(String promotion) {
        notifyObservers("New promotion: " + promotion);
    }

    public void productAvailabilityChange(String product, boolean isAvailable) {
        String availability = isAvailable ? "available" : "unavailable";
        notifyObservers("Product " + product + " is now " + availability);
    }
}
