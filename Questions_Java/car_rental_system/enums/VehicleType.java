package car_rental_system.enums;

public enum VehicleType {
    SEDAN("Sedan"),
    SUV("SUV"),
    TRUCK("Truck"),
    HATCHBACK("Hatchback");

    private final String typeName;

    VehicleType(String typeName) {
        this.typeName = typeName;
    }

    public String getDisplayName() {
        return typeName;
    }
}