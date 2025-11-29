package parking_lot.interfaces;
public interface PricingStrategy {
    double calculateFee(long parkingDurationInHours);
}