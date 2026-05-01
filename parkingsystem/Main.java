package parkingsystem;


/*
    Design a Parking System

    Requirements:

    1. There can 3 types of parking slots (small,medium,large).
    2. There can be different methods for charging like hourly payment,flat payment etc.
    3. We are considering single floor here.
    4. User should get a receipt when parked.


---------------------- Class Design:-------------------------------------------

class Slot:
   - id : int
   - size : slotType
   - isOccupied : boolean
   - vehicle: Vehicle

   + getId()
   + getSize()
   + canFit()
   + Slot(id,size)
   + park(Vehicle vehicle)
   + unpark(Vehicle vehicle)

abstract class Vehicle:
    - licensePlate : String
    - type: VehicleType

    + Vehicle(licensePlate,type)
    + getLicensePlate()
    + getType()

class Ticket:
     - ticketId : int
     - slotId : int
     - licensePlate : string
     - VehicleType : VechicleType
     - entryTime : long

     + Ticket(slotId,Vehicle V)

interface PricingStrategy:
    + double calculateCost(long parkingDuration,VehicleType type)


class ParkingLot/ParkingController:

    - slots : List<slots>
    - activeTicket : List<string,Ticket>

    + getInstance() : return ParkingLot()
    + initiaseSlots(small,medium,large)
    + findSlot()
    + parkVehcicle()
    + unparkVehicle()
    
enum VehicleType:
BIKE,
CAR,
TRUCK

enum slotType:
 Small,
 Medium,
 Large


*/

import parkingsystem.vehicleclass.Car;
import parkingsystem.vehicleclass.Vehicle;

public class Main {
    
    public static void main(String [] args)
    {
        System.out.println("Hello World");
        ParkingLot lot = ParkingLot.getInstance();
         lot.initializeSlots(5, 5, 5);

        // 1. Create a Car
        Vehicle car = new Car("MH-12-AB-1234");
        
        // 2. Park it (Using Default Hourly Strategy)
         ParkingTicket ticket = lot.parkVehicle(car);
    }
}
