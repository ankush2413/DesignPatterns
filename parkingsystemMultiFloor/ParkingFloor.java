package parkingsystemMultiFloor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import parkingsystemMultiFloor.ParkingSlot;
import parkingsystemMultiFloor.enums.SlotSize;

public class ParkingFloor {
    private int floorId;
    // Fast lookup for slots by type to avoid iterating over all slots
    private Map<SlotSize, List<ParkingSlot>> slotsByType;

    // Atomic counters to quickly check if a floor is full without locking
    private Map<SlotSize, AtomicInteger> availableSlotsCount;

    public ParkingFloor(int floorId, Map<SlotSize, Integer> capacityMap) {
        this.floorId = floorId;
        this.slotsByType = new HashMap<>();
        
        // Use ConcurrentHashMap for thread-safe reads/writes of the capacity counters
        this.availableSlotsCount = new ConcurrentHashMap<>();

        // Iterate through the requested capacities (e.g., 10 SMALL, 5 MEDIUM)
        for (Map.Entry<SlotSize, Integer> entry : capacityMap.entrySet()) {
            SlotSize type = entry.getKey();
            int numberOfSlots = entry.getValue();
            
            List<ParkingSlot> slotsForThisType = new ArrayList<>();
            
            // Create the individual Slot objects
            for (int i = 1; i <= numberOfSlots; i++) {
                // Generate a readable ID like "F1-MEDIUM-5"
               // String slotId = "F" + floorId + "-" + type.name() + "-" + i;
                int slotId = floorId+((int)(Math.random() * 200));
                slotsForThisType.add(new ParkingSlot(slotId, floorId, type));
            }
            
            // Store the created slots
            slotsByType.put(type, slotsForThisType);
            
            // Initialize the atomic counter for fast capacity checking
            availableSlotsCount.put(type, new AtomicInteger(numberOfSlots));
        }
    }


    public boolean hasSpace(SlotSize type) {
        return availableSlotsCount.get(type).get() > 0;
    }

    public void decrementAvailableCount(SlotSize type) {
        availableSlotsCount.get(type).decrementAndGet();
    }
    
    // Increment counter when vehicle leaves
    public void incrementAvailableCount(SlotSize type) {
        availableSlotsCount.get(type).incrementAndGet();
    }
    
    public List<ParkingSlot> getSlots(SlotSize type) { return slotsByType.get(type); }

    public int getFloorId() { return floorId; }

}
