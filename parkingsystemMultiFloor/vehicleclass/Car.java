package parkingsystemMultiFloor.vehicleclass;

import parkingsystemMultiFloor.enums.VehicleType;

public class Car extends Vehicle {
    public Car(String licensePlate)
    {
        super(licensePlate,VehicleType.CAR);
    }
}
