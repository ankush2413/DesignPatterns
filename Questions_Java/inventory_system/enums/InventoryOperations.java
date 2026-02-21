package inventory_system.enums;

public enum InventoryOperations{

    ADD("add"),
    Remove("remove"),
    TRANSFER("transfer"),
    ADJUST("adjust");

    private final String value;

    InventoryOperations(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}