package parkingsystemMultiFloor;

import java.util.UUID;
import parkingsystemMultiFloor.enums.VehicleType;
import parkingsystemMultiFloor.vehicleclass.Vehicle;


public class ParkingTicket {
    String ticketId;
    int slotId;
    String licensePlate;
    VehicleType vehicleType;
    long entryTime;

    public ParkingTicket(int slotId, Vehicle v) {
        this.ticketId = UUID.randomUUID().toString();
        this.slotId = slotId;
        this.licensePlate = v.getLicensePlate();
        this.vehicleType = v.getType();
        this.entryTime = System.currentTimeMillis();
    }

    public String getTicketId()
    {
        return ticketId;
    }

    public int getSotId()
    {
        return slotId;
    }
}
