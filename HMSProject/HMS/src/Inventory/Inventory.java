package Inventory;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;



public class Inventory implements Inter_Inventory {
    private static final String MEDICATION_PATH = "data"+File.separator+"Medicine_List.csv";
    private List<Medication> Inventory = new ArrayList<>();
    private HashMap<String, Integer> replenishmentRequests;

    public Inventory() {
        replenishmentRequests = new HashMap<>();
    }
      
    /**
     * 
     */
    @Override
    public HashMap<String, Integer> getReplenishmentRequests() {
        return replenishmentRequests;
    }
    @Override
    public void loadMedicationList() {
        try (BufferedReader br = new BufferedReader(new FileReader(MEDICATION_PATH))) {
        
            String line = br.readLine(); 
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                if (values.length == 3) {
                   
                   Medication medication= new Medication(values[0],Integer.parseInt(values[1]),Integer.parseInt(values[2]));
                    Inventory.add(medication);
                    
                }
            }
            System.out.println("Inventory imported successfully.");
        } catch (IOException e) {
            System.err.println("Error reading inventory list: " + e.getMessage());
        }
       }
       @Override
public List<Medication> getMedicationList() {
    return Inventory;
}


@Override
public void submitReplenishmentRequest(String medicineName, int quantity){
    boolean exists = false;
    for (Medication medication : Inventory) {
        if (medication.getMedicineName().equalsIgnoreCase(medicineName)) {
            exists = true;
            break;
        }
    }

    if (!exists) {
        System.err.println("Error: Medicine \"" + medicineName + "\" does not exist in the inventory.");
        return;
    }
    replenishmentRequests.put(medicineName, quantity);
    System.out.println("Replenishment request submitted: " + medicineName + ", Quantity: " + quantity);
}
@Override
public void viewReplenishmentRequests() {
    if (replenishmentRequests.isEmpty()) {
        System.out.println("No replenishment requests submitted.");
    } else {
        System.out.println("Replenishment Requests:");
        for (String medicine : replenishmentRequests.keySet()) {
            System.out.println("Medicine: " + medicine + ", Quantity: " + replenishmentRequests.get(medicine));
        }
    }
}
@Override
public void addMedication(String medicineName, int initialStock, int lowStockAlert) {
    for (Medication medication : Inventory) {
        if (medication.getMedicineName().equalsIgnoreCase(medicineName)) {
            System.err.println("Error: Medication \"" + medicineName + "\" already exists in the inventory.");
            return;
        }
    }
    Inventory.add(new Medication(medicineName, initialStock, lowStockAlert));
    System.out.println("Medication \"" + medicineName + "\" added successfully.");
}
@Override
public void removeMedication(String medicineName) {
    for (Medication medication : Inventory) {
        if (medication.getMedicineName().equalsIgnoreCase(medicineName)) {
           Inventory.remove(medication);
            System.out.println("Medication \"" + medicineName + "\" removed successfully.");
            return;
        }
    }
    System.err.println("Error: Medication \"" + medicineName + "\" does not exist in the inventory.");
}
@Override
public void updateStockLevel(String medicineName, int newStockLevel) {
    for (Medication medication : Inventory) {
        if (medication.getMedicineName().equalsIgnoreCase(medicineName)) {
            medication.setInitialStock(newStockLevel);
            System.out.println("Stock level for \"" + medicineName + "\" updated to " + newStockLevel + ".");
            return;
        }
    }
    System.err.println("Error: Medication \"" + medicineName + "\" does not exist in the inventory.");
}
@Override
public void updateLowStockAlert(String medicineName, int newLowStockAlert) {
    for (Medication medication : Inventory) {
        if (medication.getMedicineName().equalsIgnoreCase(medicineName)) {
            medication.setLowStockLevelAlert(newLowStockAlert);
            System.out.println("Low stock alert for \"" + medicineName + "\" updated to " + newLowStockAlert + ".");
            return;
        }
    }
    System.err.println("Error: Medication \"" + medicineName + "\" does not exist in the inventory.");
}
@Override
public void viewMedicationInventory() {
    if (Inventory.isEmpty()) {
        System.out.println("No medications in the inventory.");
    } else {
        System.out.println("Medication Inventory:");
        for (Medication medication : Inventory) {
            System.out.println(medication);
        }
    }
}

}



