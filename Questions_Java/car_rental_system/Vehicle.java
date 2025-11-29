package car_rental_system;
import car_rental_system.interfaces.PricingStrategy;
import car_rental_system.enums.VehicleType;
import car_rental_system.*;

public class Vehicle{
    private final String licenseNumber;
    private final VehicleType vehicleType;
    private final PricingStrategy pricingStrategy;
    private final String vehicleId;
    private boolean available;

    public Vehicle(String vehicleId, String licenseNumber, VehicleType vehicleType, PricingStrategy pricingStrategy) {
        this.vehicleId = vehicleId;
        this.licenseNumber = licenseNumber;
        this.vehicleType = vehicleType;
        this.pricingStrategy = pricingStrategy;
        this.available = true;
    }

    public String getVehicleId() {
        return vehicleId;
    }

    public String getLicenseNumber() {
        return licenseNumber;
    }

    public VehicleType getVehicleType() {
        return vehicleType;
    }

    public double getPrice() {
        return pricingStrategy.getprice();
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    @Override
    public String toString() {
        return String.format("%s - %s - $%.2f - Available: %b",
                vehicleType.getDisplayName(),
                licenseNumber,
                getPrice(),
                available);
    }
}