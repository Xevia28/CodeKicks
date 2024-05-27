package strategy;

public class MPayPaymentStrategy implements PaymentStrategy {
    private String mpayAccountNumber;

    public MPayPaymentStrategy(String mpayAccountNumber) {
        this.mpayAccountNumber = mpayAccountNumber;
    }

    @Override
    public boolean pay(double amount) {
        System.out.println("Paid " + amount + " using MPay account: " + mpayAccountNumber);
        return true;
    }
}
