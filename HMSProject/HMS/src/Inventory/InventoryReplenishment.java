package Inventory;

import java.util.HashMap;

public interface InventoryReplenishment {
    HashMap<String, Integer> getReplenishmentRequests();
    void submitReplenishmentRequest(String medicineName, int quantity);
    void viewReplenishmentRequests();
}
