package inventory_system.ProductReplenishStrategy;

import inventory_system.ProductFactory.Product;

public interface ReplenishmentStrategy {
// Method to replenish stock for a given product
void replenish(Product product);
}
