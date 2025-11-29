package parking_lot.enums;

public enum SlotType{
    SMALL("small"),
    MEDIUM("medium"),
    LARGE("large");

    private final String displayName;

    SlotType(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}