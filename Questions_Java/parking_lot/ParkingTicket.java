package parking_lot;
import parking_lot.interfaces.PricingStrategy;
import parking_lot.pricing.FlatRatePayment;
import parking_lot.enums.SlotType;
import parking_lot.Vehicle;

public class ParkingTicket {
    private final String ticketId;
    private final Vehicle vehicle;
    private final ParkingSlot slot;
    private final int entryTime; // simple int hours for demo
    private Integer exitTime;    // null when not exited
    private boolean paid;

    public ParkingTicket(String ticketId, Vehicle vehicle, ParkingSlot slot, int entryTime) {
        this.ticketId = ticketId;
        this.vehicle = vehicle;
        this.slot = slot;
        this.entryTime = entryTime;
        this.exitTime = null;
        this.paid = false;
    }

    public String getTicketId() {
        return ticketId;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public ParkingSlot getSlot() {
        return slot;
    }

    public int getEntryTime() {
        return entryTime;
    }

    public Integer getExitTime() {
        return exitTime;
    }

    public boolean isPaid() {
        return paid;
    }

    public void markExit(int exitTime) {
        this.exitTime = exitTime;
    }

    public void markPaid() {
        this.paid = true;
    }

    @Override
    public String toString() {
        return "Ticket{" +
                "ticketId='" + ticketId + '\'' +
                ", vehicle=" + vehicle +
                ", slot=" + slot +
                ", entryTime=" + entryTime +
                ", exitTime=" + exitTime +
                ", paid=" + paid +
                '}';
    }
}
