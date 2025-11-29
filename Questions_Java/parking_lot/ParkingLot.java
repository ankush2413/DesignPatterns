package parking_lot;

import parking_lot.enums.VehicleType;
import parking_lot.enums.SlotType;
import parking_lot.ParkingSlot;
import parking_lot.ParkingTicket;
import parking_lot.Vehicle;
import parking_lot.pricing.FlatRatePayment;
import parking_lot.interfaces.PricingStrategy;
import java.util.*;

public class ParkingLot {
    private static ParkingLot instance;
    private final List<ParkingSlot> slots;
    private final Map<String, ParkingTicket> tickets;
    private PricingStrategy paymentStrategy;

    private ParkingLot() {
        this.slots = new ArrayList<>();
        this.tickets = new HashMap<>();
        this.paymentStrategy = new FlatRatePayment(); // default
    }

    public static synchronized ParkingLot getInstance() {
        if (instance == null) {
            instance = new ParkingLot();
        }
        return instance;
    }

    public void setPaymentStrategy(PricingStrategy strategy) {
        this.paymentStrategy = strategy;
    }

    public void addSlot(ParkingSlot slot) {
        slots.add(slot);
    }

    public ParkingSlot findAvailableSlot(VehicleType vehicleType) {
        for (ParkingSlot slot : slots) {
            if (!slot.isOccupied() && isCompatible(slot.getSlotType(), vehicleType)) {
                return slot;
            }
        }
        return null;
    }

    public ParkingTicket parkVehicle(Vehicle vehicle, int entryTime) {
        ParkingSlot slot = findAvailableSlot(vehicle.getVehicleType());
        if (slot == null) {
            throw new IllegalStateException("No available slot for vehicle type: " + vehicle.getVehicleType());
        }
        slot.assignVehicle(vehicle);
        String ticketId = UUID.randomUUID().toString();
        ParkingTicket ticket = new ParkingTicket(ticketId, vehicle, slot, entryTime);
        tickets.put(ticketId, ticket);
        return ticket;
    }

    public double unparkVehicle(String ticketId, int exitTime) {
        ParkingTicket ticket = tickets.get(ticketId);
        if (ticket == null || ticket.getExitTime() != null) {
            throw new IllegalStateException("Invalid or already used ticket.");
        }
        ticket.markExit(exitTime);
        int duration = Math.max(1, exitTime - ticket.getEntryTime());
        double fee = paymentStrategy.calculateFee(duration);
        ticket.getSlot().removeVehicle();
        ticket.markPaid();
        return fee;
    }

    private boolean isCompatible(SlotType slotType, VehicleType vehicleType) {
        switch (vehicleType) {
            case TWO_WHEELER:
                return slotType == SlotType.SMALL;
            case FOUR_WHEELER:
                return slotType == SlotType.MEDIUM;
            case HEAVY_VEHICLE:
                return slotType == SlotType.LARGE;
            default:
                return false;
        }
    }

    // helper for debug/demo
    public void showSlots() {
        System.out.println("Parking slots:");
        for (ParkingSlot s : slots) {
            System.out.println(s);
        }
    }
}
