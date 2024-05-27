package strategy;

public class MBobPaymentStrategy implements PaymentStrategy {
    private String mpayAccountNumber;

    public MBobPaymentStrategy(String mpayAccountNumber) {
        this.mpayAccountNumber = mpayAccountNumber;
    }

    @Override
    public boolean pay(double amount) {
        System.out.println("Paid " + amount + " using MBob account: " + mpayAccountNumber);
        return true;
    }
}
