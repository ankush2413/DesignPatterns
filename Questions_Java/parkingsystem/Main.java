package parkingsystem;
import parkingsystem.vehicle_class.Car;
import parkingsystem.vehicle_class.Vehicle;
public class Main{

    public static void main(String[] args) {
        ParkingLot lot = ParkingLot.getInstance();
        lot.initializeSlots(5, 5, 5);

        // 1. Create a Car
        Vehicle car = new Car("MH-12-AB-1234");
        
        // 2. Park it (Using Default Hourly Strategy)
         ParkingTicket ticket = lot.parkVehicle(car);
        
        // // Simulate time passing (in real world this is hours)
        // try{
        //  Thread.sleep(100); }
        //  catch(Exception e){
        //     throw e;
        //  }

        // // 3. Change Strategy to PREMIUM (e.g., Weekend starts)
        // System.out.println("Admin: Switching to Premium Pricing...");
        // //lot.setPricingStrategy(new PremiumPricingStrategy());

        // // 4. Unpark (Should use Premium calculation)
        PaymentReceipt receipt = lot.unparkVehicle(ticket.ticketId);
        
         System.out.println(receipt);
    }
}