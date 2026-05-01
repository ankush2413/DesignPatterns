package parkingsystem.interfaces;

import parkingsystem.enums.VehicleType;

public interface PricingStrategy {
    double calculateCost(long parkingDurationInHours,VehicleType type);
}
