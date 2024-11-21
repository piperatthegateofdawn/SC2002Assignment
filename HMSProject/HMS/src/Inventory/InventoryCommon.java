package Inventory;

import java.util.List;

public interface InventoryCommon {
    void loadMedicationList();
    void viewMedicationInventory();
    List<Medication> getMedicationList();
}

