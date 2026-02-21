package factory_pattern;
import factory_pattern.cardpayment;
import factory_pattern.interfaces.payment;
import factory_pattern.upipayment;

public class paymentfactory{

    public static payment getPaymentMethod(String type)
    {
        if(type==null)
        {
            throw(new IllegalArgumentException("Payment type cannot be null"));
        }
        if(type.equalsIgnoreCase("CARD"))
        {
            return new cardpayment();
        }
        else if(type.equalsIgnoreCase("UPI"))
        {
            return new upipayment();
        }
        throw(new IllegalArgumentException("Invalid payment type"));
    }

}