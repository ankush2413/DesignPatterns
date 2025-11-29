package parking_lot;
import parking_lot.enums.VehicleType;

public class Vehicle{
    private final String vehicleNumber;
    private final VehicleType vehicleType;

    Vehicle(String vehicleNumber, VehicleType vehicleType){
        this.vehicleNumber = vehicleNumber;
        this.vehicleType = vehicleType;
    }

    public String getVehicleNumber() {
        return vehicleNumber;
    }

    public VehicleType getVehicleType() {
        return vehicleType;
    }

    @Override
    public String toString() {
        return vehicleType.getDisplayName() + " - " + vehicleNumber;
    }
}