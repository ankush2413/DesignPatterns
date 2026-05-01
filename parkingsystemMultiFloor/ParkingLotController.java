package parkingsystemMultiFloor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import parkingsystemMultiFloor.enums.SlotSize;
import parkingsystemMultiFloor.HourlyPricingStrategy;
import parkingsystemMultiFloor.interfaces.SlotAssignmentStrategy;
import parkingsystemMultiFloor.vehicleclass.Vehicle;
import parkingsystemMultiFloor.LowestFloorFirstStrategy;
import parkingsystemMultiFloor.interfaces.PricingStrategy;

public class ParkingLotController {
    private static ParkingLotController instance;
    private List<ParkingFloor> floors;
    
    // Concurrent map to handle simultaneous entry/exit ticket updates
    private ConcurrentHashMap<String, ParkingTicket> activeTickets; 
    private HashMap<String,ParkingSlot>assignedSlot;
    private SlotAssignmentStrategy assignmentStrategy;
    private PricingStrategy pricingStrategy;

    private ParkingLotController() {
        this.floors = new ArrayList<>();
        this.activeTickets = new ConcurrentHashMap<>();
        this.assignmentStrategy = new LowestFloorFirstStrategy();
        this.assignedSlot = new HashMap<>();
        this.pricingStrategy = new HourlyPricingStrategy();
    }

    public static synchronized ParkingLotController getInstance() {
        if (instance == null) {
            instance = new ParkingLotController();
        }
        return instance;
    }

    public void initializeLot(int numberOfFloors, Map<SlotSize, Integer> perFloorCapacity) {
        // Clear any existing floors if this is a re-initialization
        this.floors.clear();
        
        for (int i = 1; i <= numberOfFloors; i++) {
            ParkingFloor newFloor = new ParkingFloor(i, perFloorCapacity);
            this.floors.add(newFloor);
            System.out.println("Initialized Floor " + i + " with slots.");
        }
    }

    // Thread-safe parking operation
    public ParkingTicket parkVehicle(Vehicle vehicle) {
        // 1. Use strategy to find an available slot
        ParkingSlot slot = assignmentStrategy.findSlot(floors, vehicle.getType());
        
        if (slot == null) {
            return null;
           // return new Exception("Parking Full for vehicle type: " + vehicle.getType());
        }

        // 2. Attempt to park (the Slot class handles the lock to prevent race conditions)
        boolean success = slot.park(vehicle);
        
        if (success) {
            // 3. Update floor capacity
            getFloor(slot.getFloorId()).decrementAvailableCount(slot.getSize());
            
            // 4. Generate and store ticket
            ParkingTicket ticket = new ParkingTicket(slot.getId(), vehicle);
            activeTickets.put(ticket.getTicketId(), ticket);
            assignedSlot.put(ticket.getTicketId(),slot);
            return ticket;
        } else {
            // Race condition lost! Another car took the exact slot a millisecond prior.
            // Retry parking recursively or via a loop.
            return parkVehicle(vehicle); 
        }
    }

    private ParkingFloor getFloor(int floorId) {
    for (ParkingFloor floor : floors) {
        if (floor.getFloorId() == floorId) {
            return floor;
        }
    }
    return null;
   // throw new IllegalArgumentException("Invalid floor ID: " + floorId);
}


    public double unparkVehicle(String ticketId) {
        ParkingTicket ticket = activeTickets.get(ticketId);
        if (ticket == null) return 0;

       ParkingSlot slot = assignedSlot.get(ticketId);

       if(slot==null)return -1;
        
        // 1. Calculate fee
        long exitTime = System.currentTimeMillis();
        long duration = exitTime - ticket.entryTime;

        double fee = pricingStrategy.calculateCost(duration,ticket.vehicleType);
        
      
        // 2. Free up the slot (Thread-safe)
        slot.unpark();
        
        // 3. Update floor capacity
        getFloor(slot.getFloorId()).incrementAvailableCount(slot.getSize());
       
        
        // 4. Remove ticket
        activeTickets.remove(ticketId);
        
        return fee;
    }
}
