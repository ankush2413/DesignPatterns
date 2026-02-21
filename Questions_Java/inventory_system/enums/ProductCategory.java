package inventory_system.enums;

public enum ProductCategory{

    ELECTRONICS("electronic"),
    CLOTHING("cloth"),
    GROCERY("grocery"),
    FURNITURE("furniture"),
    OTHER("other");

    private final String value;

    ProductCategory(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}