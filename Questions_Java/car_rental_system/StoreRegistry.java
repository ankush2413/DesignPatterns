package car_rental_system;

import java.util.HashMap;
import java.util.Map;

public class StoreRegistry {
    private static StoreRegistry instance;
    private final Map<String, Store> stores;

    private StoreRegistry() {
        stores = new HashMap<>();
    }

    public static synchronized StoreRegistry getInstance() {
        if (instance == null) {
            instance = new StoreRegistry();
        }
        return instance;
    }

    public void registerStore(Store store) {
        stores.put(store.getStoreId(), store);
    }

    public Store getStore(String storeId) {
        return stores.get(storeId);
    }
}
