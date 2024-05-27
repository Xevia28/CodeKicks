package strategy;

public class PaymentProcessor {
    private PaymentStrategy strategy;

    public void setPaymentStrategy(PaymentStrategy strategy) {
        this.strategy = strategy;
    }

    public boolean executePayment(double amount) {
        if (strategy != null) {
            return strategy.pay(amount);
        } else {
            System.out.println("Payment strategy is not set.");
            return false;
        }
    }
}
