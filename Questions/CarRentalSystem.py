from abc import ABC, abstractmethod
from enum import Enum
import uuid

# --- Enums ---
class VehicleType(Enum):
    SEDAN = "Sedan"
    SUV = "SUV"
    HATCHBACK = "Hatchback"

# --- Strategy Pattern: Pricing Strategy ---
class PricingStrategy(ABC):
    @abstractmethod
    def get_rate(self) -> float:
        pass

class SedanPricing(PricingStrategy):
    def get_rate(self) -> float:
        return 40.0

class SuvPricing(PricingStrategy):
    def get_rate(self) -> float:
        return 60.0

class HatchbackPricing(PricingStrategy):
    def get_rate(self) -> float:
        return 30.0

# --- Vehicle Base Class ---
class Vehicle:
    def __init__(self, vehicle_id: str, license_number: str, vehicle_type: VehicleType, pricing_strategy: PricingStrategy):
        self.vehicle_id = vehicle_id
        self.license_number = license_number
        self.vehicle_type = vehicle_type
        self.pricing_strategy = pricing_strategy
        self.available = True

    def get_price(self):
        return self.pricing_strategy.get_rate()

    def __str__(self):
        return f"{self.vehicle_type.value} - {self.license_number} - ${self.get_price()} - Available: {self.available}"

# --- Factory Pattern: Vehicle Factory ---
class VehicleFactory:
    @staticmethod
    def create_vehicle(vehicle_type: VehicleType, license_number: str) -> Vehicle:
        vehicle_id = str(uuid.uuid4())
        if vehicle_type == VehicleType.SEDAN:
            return Vehicle(vehicle_id, license_number, vehicle_type, SedanPricing())
        elif vehicle_type == VehicleType.SUV:
            return Vehicle(vehicle_id, license_number, vehicle_type, SuvPricing())
        elif vehicle_type == VehicleType.HATCHBACK:
            return Vehicle(vehicle_id, license_number, vehicle_type, HatchbackPricing())
        else:
            raise ValueError("Unsupported vehicle type")

# --- Store Class ---
class Store:
    def __init__(self, store_id: str, location: str):
        self.store_id = store_id
        self.location = location
        self.vehicles = []
        self.bookings = {}  # booking_id -> vehicle

    def add_vehicle(self, vehicle: Vehicle):
        self.vehicles.append(vehicle)

    def show_inventory(self):
        print(f"Store {self.store_id} - {self.location} Inventory:")
        for v in self.vehicles:
            print(v)

    def rent_vehicle(self, license_number: str):
        for v in self.vehicles:
            if v.license_number == license_number and v.available:
                v.available = False
                booking_id = str(uuid.uuid4())
                self.bookings[booking_id] = v
                return booking_id, v
        return None, None

    def cancel_booking(self, booking_id: str):
        if booking_id in self.bookings:
            vehicle = self.bookings.pop(booking_id)
            vehicle.available = True
            return True
        return False

    def update_booking(self, booking_id: str, new_license_number: str):
        if booking_id in self.bookings:
            old_vehicle = self.bookings[booking_id]
            old_vehicle.available = True
            new_vehicle = next((v for v in self.vehicles if v.license_number == new_license_number and v.available), None)
            if new_vehicle:
                new_vehicle.available = False
                self.bookings[booking_id] = new_vehicle
                return True
        return False

# --- Singleton Pattern: Store Registry ---
class StoreRegistry:
    __instance = None

    @staticmethod
    def get_instance():
        if StoreRegistry.__instance is None:
            StoreRegistry()
        return StoreRegistry.__instance

    def __init__(self):
        if StoreRegistry.__instance is not None:
            raise Exception("This class is a singleton!")
        else:
            StoreRegistry.__instance = self
            self.stores = {}

    def register_store(self, store: Store):
        self.stores[store.store_id] = store

    def get_store(self, store_id: str):
        return self.stores.get(store_id)

# --- Main Function to Simulate the System ---
if __name__ == '__main__':
    # Singleton Store Registry
    registry = StoreRegistry.get_instance()

    # Create two stores
    store1 = Store("store1", "New York")
    store2 = Store("store2", "San Francisco")
    registry.register_store(store1)
    registry.register_store(store2)

    # Add vehicles using Factory
    vehicles = [
        (VehicleType.SEDAN, "NY1234"),
        (VehicleType.SUV, "NY5678"),
        (VehicleType.HATCHBACK, "NY9101")
    ]

    for vehicle_type, license_num in vehicles:
        store1.add_vehicle(VehicleFactory.create_vehicle(vehicle_type, license_num))

    for vehicle_type, license_num in vehicles:
        store2.add_vehicle(VehicleFactory.create_vehicle(vehicle_type, "SF" + license_num[2:]))

    # Show inventory
    store1.show_inventory()
    store2.show_inventory()

    # Simulate renting a vehicle
    print("\nRenting a vehicle from Store 1...")
    booking_id, rented_vehicle = store1.rent_vehicle("NY1234")
    print(f"Rented: {rented_vehicle}, Booking ID: {booking_id}")

    print("\nInventory after rental:")
    store1.show_inventory()

    # Simulate updating booking
    print("\nUpdating the booking to another vehicle...")
    if store1.update_booking(booking_id, "NY5678"):
        print("Booking updated successfully.")
    else:
        print("Failed to update booking.")

    # Simulate cancelling booking
    print("\nCancelling the booking...")
    if store1.cancel_booking(booking_id):
        print("Booking cancelled successfully.")
    else:
        print("Failed to cancel booking.")

    print("\nFinal Inventory:")
    store1.show_inventory()
