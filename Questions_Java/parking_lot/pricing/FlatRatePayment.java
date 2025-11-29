package parking_lot.pricing;
import parking_lot.interfaces.PricingStrategy;

public class FlatRatePayment implements PricingStrategy{
    private final double flatRate;

    public FlatRatePayment() {
        this(50.0); // Default flat rate
    }

    public FlatRatePayment(double flatRate) {
        this.flatRate = flatRate;
    }
    @Override
    public double calculateFee(long parkingDurationInHours) {
        return flatRate;
    }
}