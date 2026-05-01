package parkingsystemMultiFloor;

import java.util.List;

import parkingsystemMultiFloor.interfaces.SlotAssignmentStrategy;
import parkingsystemMultiFloor.enums.SlotSize;
import parkingsystemMultiFloor.enums.VehicleType;
import parkingsystemMultiFloor.ParkingSlot;

public class LowestFloorFirstStrategy implements SlotAssignmentStrategy {
    
    @Override
    public ParkingSlot findSlot(List<ParkingFloor> floors, VehicleType type) {
        // 1. Determine the required slot size for the vehicle
        SlotSize requiredSlotType = getSlotTypeForVehicle(type);

        // 2. Iterate through floors starting from the lowest level
        for (ParkingFloor floor : floors) {
            
            // Fast check: Does this floor even have space for this type?
            // (This uses the AtomicInteger we set up earlier)
            if (floor.hasSpace(requiredSlotType)) {
                
                // 3. Find the exact slot
                for (ParkingSlot slot : floor.getSlots(requiredSlotType)) {
                    // Quick heuristic check. The actual thread-safe locking 
                    // happens in the controller when it calls slot.park()
                    if (!slot.getisOccupied()) {
                        return slot;
                    }
                }
            }
        }
        
        // If we loop through everything and find nothing, the lot is full
        return null; 
    }

    // Helper method to map vehicles to slot sizes
    private SlotSize getSlotTypeForVehicle(VehicleType type) {
        switch (type) {
            case BIKE: 
                return SlotSize.SMALL;
            case CAR: 
                return SlotSize.MEDIUM; // Note: A robust system might allow CARs in LARGE slots if MEDIUM is full
            case TRUCK: 
                return SlotSize.LARGE;
            default: 
                throw new IllegalArgumentException("Unknown vehicle type");
        }
    }

}
