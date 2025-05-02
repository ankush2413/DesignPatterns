from abc import ABC, abstractmethod
from enum import Enum
import uuid

# --- Enums ---
class VehicleType(Enum):
    TWO_WHEELER = "Two-Wheeler"
    FOUR_WHEELER = "Four-Wheeler"
    TRUCK = "Truck"

class SlotType(Enum):
    SMALL = "Small"
    MEDIUM = "Medium"
    LARGE = "Large"

# --- Vehicle ---
class Vehicle:
    def __init__(self, vehicle_number: str, vehicle_type: VehicleType):
        self.vehicle_number = vehicle_number
        self.vehicle_type = vehicle_type

# --- Parking Slot ---
class ParkingSlot:
    def __init__(self, slot_id: str, slot_type: SlotType):
        self.slot_id = slot_id
        self.slot_type = slot_type
        self.is_occupied = False
        self.parked_vehicle = None

    def assign_vehicle(self, vehicle: Vehicle):
        self.is_occupied = True
        self.parked_vehicle = vehicle

    def remove_vehicle(self):
        self.is_occupied = False
        self.parked_vehicle = None

# --- Strategy Pattern: Payment Strategy ---
class PaymentStrategy(ABC):
    @abstractmethod
    def calculate_fee(self, duration_hours: int) -> float:
        pass

class HourlyPayment(PaymentStrategy):
    def calculate_fee(self, duration_hours: int) -> float:
        return 10.0 * duration_hours

class FlatRatePayment(PaymentStrategy):
    def calculate_fee(self, duration_hours: int) -> float:
        return 50.0

# --- Ticket ---
class ParkingTicket:
    def __init__(self, ticket_id: str, vehicle: Vehicle, slot: ParkingSlot, entry_time: int):
        self.ticket_id = ticket_id
        self.vehicle = vehicle
        self.slot = slot
        self.entry_time = entry_time
        self.exit_time = None
        self.paid = False

    def mark_exit(self, exit_time: int):
        self.exit_time = exit_time

# --- Singleton: ParkingLot ---
class ParkingLot:
    __instance = None

    @staticmethod
    def get_instance():
        if ParkingLot.__instance is None:
            ParkingLot()
        return ParkingLot.__instance

    def __init__(self):
        if ParkingLot.__instance is not None:
            raise Exception("This class is a singleton!")
        else:
            ParkingLot.__instance = self
            self.slots = []
            self.tickets = {}
            self.payment_strategy = HourlyPayment()  # default

    def set_payment_strategy(self, strategy: PaymentStrategy):
        self.payment_strategy = strategy

    def add_slot(self, slot: ParkingSlot):
        self.slots.append(slot)

    def find_available_slot(self, vehicle_type: VehicleType) -> ParkingSlot:
        for slot in self.slots:
            if not slot.is_occupied and self._is_compatible(slot.slot_type, vehicle_type):
                return slot
        return None

    def park_vehicle(self, vehicle: Vehicle, entry_time: int) -> ParkingTicket:
        slot = self.find_available_slot(vehicle.vehicle_type)
        if not slot:
            raise Exception("No available slot for this vehicle type.")
        slot.assign_vehicle(vehicle)
        ticket_id = str(uuid.uuid4())
        ticket = ParkingTicket(ticket_id, vehicle, slot, entry_time)
        self.tickets[ticket_id] = ticket
        return ticket

    def unpark_vehicle(self, ticket_id: str, exit_time: int) -> float:
        ticket = self.tickets.get(ticket_id)
        if not ticket or ticket.exit_time:
            raise Exception("Invalid or already used ticket.")
        ticket.mark_exit(exit_time)
        duration = max(1, exit_time - ticket.entry_time)
        fee = self.payment_strategy.calculate_fee(duration)
        ticket.slot.remove_vehicle()
        ticket.paid = True
        return fee

    def _is_compatible(self, slot_type: SlotType, vehicle_type: VehicleType) -> bool:
        if vehicle_type == VehicleType.TWO_WHEELER:
            return slot_type == SlotType.SMALL
        elif vehicle_type == VehicleType.FOUR_WHEELER:
            return slot_type == SlotType.MEDIUM
        elif vehicle_type == VehicleType.TRUCK:
            return slot_type == SlotType.LARGE
        return False

# --- Main Execution (Simulation) ---
if __name__ == '__main__':
    lot = ParkingLot.get_instance()

    # Add slots
    lot.add_slot(ParkingSlot("S1", SlotType.SMALL))
    lot.add_slot(ParkingSlot("M1", SlotType.MEDIUM))
    lot.add_slot(ParkingSlot("L1", SlotType.LARGE))

    # Set payment strategy
    lot.set_payment_strategy(HourlyPayment())

    # Park vehicle
    vehicle = Vehicle("KA01AB1234", VehicleType.FOUR_WHEELER)
    ticket = lot.park_vehicle(vehicle, entry_time=1)
    print(f"Vehicle parked with ticket: {ticket.ticket_id}")

    # Unpark vehicle
    fee = lot.unpark_vehicle(ticket.ticket_id, exit_time=5)
    print(f"Parking fee: ${fee}")
