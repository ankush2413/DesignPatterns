package parkingsystemMultiFloor.interfaces;

import parkingsystemMultiFloor.enums.VehicleType;

public interface  PricingStrategy {
     double calculateCost(long parkingDurationInHours,VehicleType type);
}
