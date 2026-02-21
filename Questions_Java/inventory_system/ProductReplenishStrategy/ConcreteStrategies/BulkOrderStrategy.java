package inventory_system.ProductReplenishStrategy.ConcreteStrategies;
import inventory_system.ProductReplenishStrategy.ReplenishmentStrategy;
import  inventory_system.ProductFactory.Product;

public class BulkOrderStrategy implements ReplenishmentStrategy{

    @Override
    public void replenish(Product product) {
        // Implement Bulk Order replenishment logic
        System.out.println("Applying Bulk Order replenishment for " + product.getName());
        // Order in large quantities to minimize order costs
    }
}