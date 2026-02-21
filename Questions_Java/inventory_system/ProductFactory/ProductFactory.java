package inventory_system.ProductFactory;
import inventory_system.ProductFactory.ConcreteProduct.ElectronicsProduct;
import inventory_system.enums.ProductCategory;

public class ProductFactory {

    public Product createProduct(ProductCategory category, String sku, String name, double price, int quantity, int threshold)
    {
        switch (category) {
            case ELECTRONICS:
                return new ElectronicsProduct(sku, name, price, quantity,threshold);
            case CLOTHING:
                return new ElectronicsProduct(sku, name, price, quantity,threshold);
            case GROCERY:
                return new ElectronicsProduct(sku, name, price, quantity,threshold);
            default:
                throw new IllegalArgumentException(
                        "Unsupported product category: " + category);
        }
    }
}