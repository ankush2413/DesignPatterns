package parkingsystemMultiFloor;

import parkingsystemMultiFloor.vehicleclass.Vehicle;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import parkingsystemMultiFloor.enums.SlotSize;


public class ParkingSlot {
    private int id;
    private int floorId;
    private SlotSize size;
    public boolean isOccupied;
    private Vehicle vehicle;
    private final Lock lock;    // Granular lock for concurrency

    public ParkingSlot(int id,int floorId, SlotSize size) { 
        this.id = id; 
        this.floorId = floorId; 
        this.size = size;
        this.isOccupied = false;
        this.lock = new ReentrantLock(); 
    }
    
    public SlotSize getSize()
    {
        return size;
    }

    public int getFloorId()
    {
        return floorId;
    }

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
    
    public boolean  park(Vehicle v)
    { lock.lock();
        try {
            if (!isOccupied) {
                this.vehicle = vehicle;
                this.isOccupied = true;
                return true;
            }
            return false; // Slot was taken by another thread just before this
        } finally {
            lock.unlock();
        }
     }

    public void unpark() {
        lock.lock();
        try {
            this.vehicle = null;
            this.isOccupied = false;
        } finally {
            lock.unlock();
        }
     }
    public int getId() { return id; }

    public boolean getisOccupied()
    {
        return isOccupied;
    }
}
