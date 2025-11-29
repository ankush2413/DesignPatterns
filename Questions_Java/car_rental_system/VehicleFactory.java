package car_rental_system;
import car_rental_system.enums.VehicleType;
import car_rental_system.interfaces.PricingStrategy;
import car_rental_system.SedanPricing;

import java.util.UUID;

public class VehicleFactory {
    public static Vehicle createVehicle(VehicleType type, String licenseNumber) {
        String vehicleId = UUID.randomUUID().toString();
        switch (type) {
            case SEDAN:
                return new Vehicle(vehicleId, licenseNumber, type, new SedanPricing());
            case SUV:
                return new Vehicle(vehicleId, licenseNumber, type, new SedanPricing());
            case HATCHBACK:
                return new Vehicle(vehicleId, licenseNumber, type, new SedanPricing());
            default:
                throw new IllegalArgumentException("Unsupported vehicle type: " + type);
        }
    }
}