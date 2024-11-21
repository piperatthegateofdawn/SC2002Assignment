package Inventory;

public interface InventoryManagement {
    void addMedication(String medicineName, int initialStock, int lowStockAlert);
    void removeMedication(String medicineName);
    void updateStockLevel(String medicineName, int newStockLevel);
}

