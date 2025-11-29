package car_rental_system;
import car_rental_system.enums.VehicleType;
import car_rental_system.*;

public class Main {
    public static void main(String[] args) {
        // Singleton Store Registry
        StoreRegistry registry = StoreRegistry.getInstance();

        // Create two stores
        Store store1 = new Store("store1", "New York");
        Store store2 = new Store("store2", "San Francisco");
        registry.registerStore(store1);
        registry.registerStore(store2);

        // Add vehicles using Factory
        VehicleType[] types = { VehicleType.SEDAN, VehicleType.SUV, VehicleType.HATCHBACK };
        String[] nyLicences = { "NY1234", "NY5678", "NY9101" };

        for (int i = 0; i < types.length; i++) {
            store1.addVehicle(VehicleFactory.createVehicle(types[i], nyLicences[i]));
        }

        // Add vehicles to store2 with SF prefix
        for (int i = 0; i < types.length; i++) {
            String sfLicense = "SF" + nyLicences[i].substring(2);
            store2.addVehicle(VehicleFactory.createVehicle(types[i], sfLicense));
        }

        // Show inventory
        store1.showInventory();
        store2.showInventory();

        // Simulate renting a vehicle
        // System.out.println("\nRenting a vehicle from Store 1...");
        // Store.RentalResult result = store1.rentVehicle("NY1234");
        // if (result != null) {
        //     System.out.println("Rented: " + result.vehicle + ", Booking ID: " + result.bookingId);
        // } else {
        //     System.out.println("Failed to rent vehicle.");
        // }

        // System.out.println("\nInventory after rental:");
        // store1.showInventory();

        // Simulate updating booking
        // System.out.println("\nUpdating the booking to another vehicle...");
        // if (result != null && store1.updateBooking(result.bookingId, "NY5678")) {
        //     System.out.println("Booking updated successfully.");
        // } else {
        //     System.out.println("Failed to update booking.");
        // }

        // // Simulate cancelling booking
        // System.out.println("\nCancelling the booking...");
        // if (result != null && store1.cancelBooking(result.bookingId)) {
        //     System.out.println("Booking cancelled successfully.");
        // } else {
        //     System.out.println("Failed to cancel booking.");
        // }

        // System.out.println("\nFinal Inventory:");
        // store1.showInventory();
    }
}
