package inventory_system;
import inventory_system.WareHouse;
import inventory_system.ProductReplenishStrategy.ReplenishmentStrategy;
import inventory_system.ProductFactory.ProductFactory;
import inventory_system.ProductFactory.Product;


import java.util.ArrayList;
import java.util.List;
public class InventoryManager{
     private static InventoryManager instance;

    // System components
    private List<WareHouse> warehouses;
    private ProductFactory productFactory;
    private ReplenishmentStrategy replenishmentStrategy;

    // Private constructor to prevent instantiation from outside
    private InventoryManager(ReplenishmentStrategy replenishmentStrategy) {
        // Initialize collections and dependencies
        warehouses = new ArrayList<>();
        productFactory = new ProductFactory();
        this.replenishmentStrategy = replenishmentStrategy;
    }

    // Static method to get the singleton instance with thread safety
    public static synchronized InventoryManager getInstance(ReplenishmentStrategy replenishmentStrategy) {
        if (instance == null) {
            instance = new InventoryManager(replenishmentStrategy);
        }
        return instance;
    }

    // Strategy pattern method
    public void setReplenishmentStrategy(ReplenishmentStrategy replenishmentStrategy) {
        this.replenishmentStrategy = replenishmentStrategy;
    }

    // Warehouse management
    public void addWarehouse(WareHouse warehouse) {
        warehouses.add(warehouse);
    }

    public void removeWarehouse(WareHouse warehouse) {
        warehouses.remove(warehouse);
    }

    // Product inventory operations
    public Product getProductBySku(String sku) {
        for (WareHouse warehouse : warehouses) {
            Product product = warehouse.getProductBySku(sku);
            if (product != null) {
                return product;
            }
        }
        return null;
    }

    // Check stock levels and apply replenishment strategy if needed
    public void checkAndReplenish(String sku) {
        Product product = getProductBySku(sku);
        if (product != null) {
            // If product is below threshold
            if (product.getQuantity() < product.getThreshold()) {
                // Apply current replenishment strategy
                if (replenishmentStrategy != null) {
                    replenishmentStrategy.replenish(product);
                }
            }
        }
    }

    // Global inventory check
    public void performInventoryCheck() {
        for (WareHouse warehouse : warehouses) {
            for (Product product : warehouse.getAllProducts()) {
                if (product.getQuantity() < product.getThreshold()) {
                    if (replenishmentStrategy != null)  replenishmentStrategy.replenish(product);
                }
            }
        }
    }
}