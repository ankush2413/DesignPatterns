package factory_pattern;
import factory_pattern.interfaces.payment;

public class cardpayment implements payment {

    @Override
    public void pay(double amount) {
        System.out.println("Paid " + amount + " using Card Payment.");
    }
}