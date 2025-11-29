package parking_lot;

import parking_lot.ParkingLot;
import parking_lot.enums.VehicleType;
import parking_lot.enums.SlotType;
import parking_lot.ParkingSlot;
import parking_lot.Vehicle;
import parking_lot.pricing.FlatRatePayment;

public class Main {
    public static void main(String[] args) {
        ParkingLot lot = ParkingLot.getInstance();

        // add slots
        lot.addSlot(new ParkingSlot("S1", SlotType.SMALL));
        lot.addSlot(new ParkingSlot("M1", SlotType.MEDIUM));
        lot.addSlot(new ParkingSlot("L1", SlotType.LARGE));

        // set payment strategy
        lot.setPaymentStrategy(new FlatRatePayment());

        // park vehicle
        Vehicle vehicle = new Vehicle("KA01AB1234", VehicleType.FOUR_WHEELER);
        var ticket = lot.parkVehicle(vehicle, 1);
        System.out.println("Vehicle parked with ticket: " + ticket.getTicketId());


        lot.showSlots();
        System.out.println("Unparking vehicle...");
        // unpark vehicle and calculate fee
        double fee = lot.unparkVehicle(ticket.getTicketId(), 5);
        System.out.println("Parking fee: $" + fee);

        // show slots final state
        lot.showSlots();
    }
}
