package parkingsystemMultiFloor;

import java.util.HashMap;
import java.util.Map;

import parkingsystemMultiFloor.vehicleclass.Car;
import parkingsystemMultiFloor.enums.SlotSize;
import parkingsystemMultiFloor.vehicleclass.Vehicle;


/*

    1. There can multiple floors.
    2. Each floor have a prefdefined different types of slots(SMALL,MEDIUM,LARGE).
    3. There can multiple entry points to parking, so make sure no slot assigned to multiple users.
    4. Using strategy pattern to have different assignement strategies and payment strategies.

*/


public class Main {
    
    public static void main(String [] args)
    {
        System.out.println("Hello Word");
        Map<SlotSize, Integer> floorCapacity = new HashMap<>();
        floorCapacity.put(SlotSize.SMALL, 50);  // 50 Bike slots
        floorCapacity.put(SlotSize.MEDIUM, 30); // 30 Car slots
        floorCapacity.put(SlotSize.LARGE, 10);  // 10 Truck slots

        // 2. Get the singleton controller instance
        ParkingLotController controller = ParkingLotController.getInstance();
        
        // 3. Set up the strategies (from previous steps)
      //  controller.setAssignmentStrategy(new LowestFloorFirstStrategy());
        // controller.setPricingStrategy(new HourlyPricingStrategy()); 
        
        // 4. Initialize a 3-story parking lot
        controller.initializeLot(3, floorCapacity);
         System.out.println("Parking Lot is open for business!");

        Vehicle car = new Car("MH-12-AB-1234");

       ParkingTicket t1 = controller.parkVehicle(car);
       System.out.println("Car Parked. Your TicketId: "+t1.getTicketId()+ " And Slot Id: "+t1.getSotId());

       Vehicle car2 = new Car("MH-12-AB-1224");
        ParkingTicket t2 = controller.parkVehicle(car2);
         System.out.println("Car Parked. Your TicketId: "+t2.getTicketId()+ " And Slot Id: "+t2.getSotId());

       double cost = controller.unparkVehicle(t1.getTicketId());

       System.out.println("Cost: "+cost);
       
        
    }
}