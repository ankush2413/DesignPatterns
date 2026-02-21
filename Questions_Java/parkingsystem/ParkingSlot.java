package parkingsystem;
import parkingsystem.vehicle_class.Vehicle;
import parkingsystem.enums.SlotSize;

class ParkingSlot {
    private int id;
    private SlotSize size;
    private boolean isOccupied;
    private Vehicle vehicle;

    public ParkingSlot(int id, SlotSize size) { this.id = id; this.size = size; }
    
    public boolean canFit(Vehicle v) {
        if (isOccupied) return false;
        // Simple logic: Bike fits in Small+, Car in Medium+, Truck in Large
        switch (v.getType()) {
            case TRUCK: return size == SlotSize.LARGE;
            case CAR:   return size == SlotSize.MEDIUM || size == SlotSize.LARGE;
            case BIKE:  return true; // Bike fits everywhere
            default:    return false;
        }
    }
    
    public void park(Vehicle v) { this.vehicle = v; this.isOccupied = true; }
    public void unpark() { this.vehicle = null; this.isOccupied = false; }
    public int getId() { return id; }
}