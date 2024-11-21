package Inventory;

public class Medication {
    private String MedicineName;
    private int InitialStock;
    private int LowStockLevelAlert;

    public Medication(String medicineName, int initialStock, int lowStockLevelAlert) {
        MedicineName = medicineName;
        InitialStock = initialStock;
        LowStockLevelAlert = lowStockLevelAlert;
    }

    public String getMedicineName() {
        return MedicineName;
    }
    public int getInitialStock() {
        return InitialStock;
    }
    public int getLowStockLevelAlert() {
        return LowStockLevelAlert;
    }
    public void setInitialStock(int initialStock) {
        InitialStock = initialStock;
    }
    public void setLowStockLevelAlert(int lowStockLevelAlert) {
        LowStockLevelAlert = lowStockLevelAlert;
    }
    @Override
    public String toString() {
        return "Medicine Name: " +MedicineName + ", Initial Stock: " + InitialStock + ", Low Stock Level Alert: " +LowStockLevelAlert;
    }
}
