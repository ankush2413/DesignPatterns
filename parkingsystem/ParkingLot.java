package parkingsystem;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import parkingsystem.enums.slotSize;
import parkingsystem.interfaces.PricingStrategy;
import parkingsystem.vehicleclass.Vehicle;

public class ParkingLot {
      private static volatile ParkingLot instance;
    private List<ParkingSlot> slots;
    private Map<String, ParkingTicket> activeTickets;
    
    // STRATEGY: Hold a reference to the interface
    private PricingStrategy pricingStrategy;

    private ParkingLot() {
        slots = new ArrayList<>();
        activeTickets = new ConcurrentHashMap<>();
        // Default Strategy
        this.pricingStrategy = new HourlyPricingStrategy();
    }

    public static ParkingLot getInstance() {
        if (instance == null) {
            synchronized (ParkingLot.class) {
                if (instance == null) instance = new ParkingLot();
            }
        }
        return instance;
    }

    public void initializeSlots(int small, int medium, int large) {
        int id = 1;
        for (int i=0; i<small; i++) slots.add(new ParkingSlot(id++, slotSize.SMALL));
        for (int i=0; i<medium; i++) slots.add(new ParkingSlot(id++, slotSize.MEDIUM));
        for (int i=0; i<large; i++) slots.add(new ParkingSlot(id++, slotSize.LARGE));
    }

    // Allows Admin to change pricing dynamically
    public void setPricingStrategy(PricingStrategy strategy) {
        this.pricingStrategy = strategy;
    }

    public ParkingTicket parkVehicle(Vehicle v) {
        ParkingSlot slot = findSlot(v);
        if (slot == null) return null;

        slot.park(v);
        ParkingTicket ticket = new ParkingTicket(slot.getId(), v);
        activeTickets.put(ticket.ticketId, ticket);
        System.out.println("Parked " + v.getType() + " at Slot " + slot.getId());
        return ticket;
    }

    public boolean  unparkVehicle(String ticketId) {
        if (!activeTickets.containsKey(ticketId)) return false;

        ParkingTicket ticket = activeTickets.get(ticketId);
        long exitTime = System.currentTimeMillis();
        long duration = exitTime - ticket.entryTime;
        
        // 1. Unpark physically
        unparkPhysicalSlot(ticket.slotId);

        // 2. Calculate Cost using the active Strategy
        double cost = pricingStrategy.calculateCost(duration, ticket.vehicleType);

        // 3. Generate Receipt & Remove ticket
        activeTickets.remove(ticketId);
        return true;
    }

    private ParkingSlot findSlot(Vehicle v) {
        for (ParkingSlot s : slots) { if (s.canFit(v)) return s; }
        return null;
    }

    private void unparkPhysicalSlot(int slotId) {
        for (ParkingSlot s : slots) {
            if (s.getId() == slotId) { s.unpark(); break; }
        }
    }
}
