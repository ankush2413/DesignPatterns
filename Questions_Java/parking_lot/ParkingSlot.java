package parking_lot;
import parking_lot.enums.SlotType;
import parking_lot.Vehicle;

public class ParkingSlot{
    private final String slotId;
    private SlotType slotType;
    private boolean isOccupied;
    private Vehicle parkedVehicle;

    public ParkingSlot(String slotId, SlotType slotType) {
        this.slotId = slotId;
        this.slotType = slotType;
        this.isOccupied = false;
        this.parkedVehicle = null;
    }

    public SlotType getSlotType() {
        return slotType;
    }

    public boolean isOccupied() {
        return isOccupied;
    }

    public Vehicle getParkedVehicle() {
        return parkedVehicle;
    }

    public void assignVehicle(Vehicle vehicle) {
        this.parkedVehicle = vehicle;
        this.isOccupied = true;
    }

    public void removeVehicle() {
        this.parkedVehicle = null;
        this.isOccupied = false;
    }

    @Override
    public String toString() {
        return slotId + " (" + slotType.getDisplayName() + ") - Occupied: " + isOccupied;
    }

}