package strategy;

public class MPayStrategy implements PaymentStrategy {
    @Override
    public void pay(double amount) {
        System.out.println("Paid " + amount + " using mPay.");
    }
}