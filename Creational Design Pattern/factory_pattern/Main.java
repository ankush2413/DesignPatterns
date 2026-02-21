

/*
Facory Pattern: Encapsulates object creation logic in one place and returns objects via an interface.


What problem does Factory solve?
 - When:
   Code depends on interfaces
   Object creation logic becomes complex
   You want to hide new from client code

*/

package factory_pattern;
import factory_pattern.interfaces.payment;

public class Main{

    public static void main(String[] args){
        
        payment paymentMethod = paymentfactory.getPaymentMethod("CARD");
        paymentMethod.pay(1000.0);

        paymentMethod = paymentfactory.getPaymentMethod("UPI");
        paymentMethod.pay(500.0);
    }
}