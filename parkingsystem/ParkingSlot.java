package parkingsystem;

import parkingsystem.enums.slotSize;
import parkingsystem.vehicleclass.Vehicle;

public class ParkingSlot {
    private int id;
    private slotSize size;
    private boolean isOccupied;
    private Vehicle vehicle;

    public ParkingSlot(int id, slotSize size) { this.id = id; this.size = size; }
    
    public boolean canFit(Vehicle v) {
        if (isOccupied) return false;
        // Simple logic: Bike fits in Small+, Car in Medium+, Truck in Large
        switch (v.getType()) {
            case TRUCK: return size == slotSize.LARGE;
            case CAR:   return size == slotSize.MEDIUM || size == slotSize.LARGE;
            case BIKE:  return true; // Bike fits everywhere
            default:    return false;
        }
    }
    
    public void park(Vehicle v) { this.vehicle = v; this.isOccupied = true; }
    public void unpark() { this.vehicle = null; this.isOccupied = false; }
    public int getId() { return id; }
}
