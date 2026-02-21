package factory_pattern;
import factory_pattern.interfaces.payment;

public class upipayment implements payment {

    @Override
    public void pay(double amount){
        System.out.println("Paid " + amount + " using UPI Payment.");
    }
}
