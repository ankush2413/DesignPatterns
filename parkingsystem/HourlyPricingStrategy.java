package parkingsystem;

import parkingsystem.enums.VehicleType;
import parkingsystem.interfaces.PricingStrategy;

public class HourlyPricingStrategy  implements PricingStrategy{
    
    @Override
    public double calculateCost(long durationInMillis, VehicleType type) {
        long hours = Math.max(1, durationInMillis / (1000 * 60 * 60)); // Min 1 hour
        
        switch (type) {
            case BIKE: return hours * 10.0; // $10 per hour
            case CAR:  return hours * 20.0; // $20 per hour
            case TRUCK:return hours * 30.0; // $30 per hour
            default:   return hours * 20.0;
        }
    }
}
