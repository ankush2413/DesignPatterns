package parking_lot.enums;

public enum VehicleType {
    TWO_WHEELER("two_wheeler"),
    FOUR_WHEELER("four_wheeler"),
    HEAVY_VEHICLE("heavy_vehicle");

    private final String displayName;

    VehicleType(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}