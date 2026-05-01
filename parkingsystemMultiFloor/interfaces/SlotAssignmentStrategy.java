package parkingsystemMultiFloor.interfaces;

import java.util.List;

import parkingsystemMultiFloor.ParkingSlot;
import parkingsystemMultiFloor.ParkingFloor;
import parkingsystemMultiFloor.enums.VehicleType;

public interface SlotAssignmentStrategy {
    ParkingSlot findSlot(List<ParkingFloor> floors, VehicleType type);
}
