package strategy;

public class MBobStrategy implements PaymentStrategy {
    @Override
    public void pay(double amount) {
        System.out.println("Paid " + amount + " using mBOB.");
    }
}