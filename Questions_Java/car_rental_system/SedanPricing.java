package car_rental_system;
import car_rental_system.interfaces.PricingStrategy;

public class SedanPricing implements PricingStrategy{
    
    @Override
    public double getprice() {
        return 50.0; // Base price for Sedan
    }
}