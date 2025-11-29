package car_rental_system;


import java.util.*;

public class Store {
    private final String storeId;
    private final String location;
    private final List<Vehicle> vehicles;
    private final Map<String, Vehicle> bookings; // bookingId -> vehicle

    public Store(String storeId, String location) {
        this.storeId = storeId;
        this.location = location;
        this.vehicles = new ArrayList<>();
        this.bookings = new HashMap<>();
    }

    public String getStoreId() {
        return storeId;
    }

    public String getLocation() {
        return location;
    }

    public void addVehicle(Vehicle v) {
        vehicles.add(v);
    }

    public void showInventory() {
        System.out.println("Store " + storeId + " - " + location + " Inventory:");
        for (Vehicle v : vehicles) {
            System.out.println(v);
        }
    }

    // helper class to return both booking id and vehicle
    public static class RentalResult {
        public final String bookingId;
        public final Vehicle vehicle;

        public RentalResult(String bookingId, Vehicle vehicle) {
            this.bookingId = bookingId;
            this.vehicle = vehicle;
        }
    }

    public RentalResult rentVehicle(String licenseNumber) {
        for (Vehicle v : vehicles) {
            if (v.getLicenseNumber().equals(licenseNumber) && v.isAvailable()) {
                v.setAvailable(false);
                String bookingId = UUID.randomUUID().toString();
                bookings.put(bookingId, v);
                return new RentalResult(bookingId, v);
            }
        }
        return null;
    }

    public boolean cancelBooking(String bookingId) {
        Vehicle v = bookings.remove(bookingId);
        if (v != null) {
            v.setAvailable(true);
            return true;
        }
        return false;
    }

    public boolean updateBooking(String bookingId, String newLicenseNumber) {
        if (!bookings.containsKey(bookingId)) {
            return false;
        }

        Vehicle oldVehicle = bookings.get(bookingId);
        // free old vehicle
        oldVehicle.setAvailable(true);

        // find a new vehicle with the license and available
        Vehicle newVehicle = null;
        for (Vehicle v : vehicles) {
            if (v.getLicenseNumber().equals(newLicenseNumber) && v.isAvailable()) {
                newVehicle = v;
                break;
            }
        }

        if (newVehicle != null) {
            newVehicle.setAvailable(false);
            bookings.put(bookingId, newVehicle);
            return true;
        } else {
            // If new vehicle not found, revert old vehicle to occupied (keeps original booking)
            oldVehicle.setAvailable(false);
            bookings.put(bookingId, oldVehicle);
            return false;
        }
    }

    public Vehicle getBookedVehicle(String bookingId) {
        return bookings.get(bookingId);
    }
}
