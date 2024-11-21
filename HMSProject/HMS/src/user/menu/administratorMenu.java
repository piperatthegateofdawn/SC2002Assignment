package user.menu;

import Inventory.Inter_Inventory;
import Inventory.Medication;
import user.staff;
import user.userIN.staffIN;
import Inventory.Inventory;

import java.util.List;
import java.util.Scanner;

import Appointment.AppointmentManager;


public class administratorMenu extends UserMenu{
    private Inter_Inventory inventory;
    public administratorMenu() {

        this.inventory = new Inventory();
        this.inventory.loadMedicationList();
   }

   private staffIN staffManager;
   private AppointmentManager appointmentManager;

   

    public administratorMenu(Inter_Inventory inventory,staffIN staffManager, AppointmentManager appointmentManager) {
        this.inventory = inventory;
        this.staffManager = staffManager;
        this.appointmentManager = appointmentManager;
        
}
        
    
@Override
    public void DisplayMenu() {
        Scanner scanner = new Scanner(System.in);
        int choice = -1;

        do {
            System.out.println("\nAdministrator Menu:");
            System.out.println("1. View and Manage Hospital Staff.");
            System.out.println("2. View Appointment details");
            System.out.println("3. View and Manage Medication Inventory");
            System.out.println("4. Approve Replenishment Request");
            System.out.println("5. Logout");

        choice = getUserChoice();

            switch (choice) {
                case 1:
                viewAndManageHospitalStaff();
                    break;
                case 2:
                    viewAppointmentDetails();
                    break;
                case 3:
                    viewAndManageMedicationInventory(); 
                        
                    break;
                case 4:
                    approveReplenishmentRequest(scanner);
                    break;
                case 5:
                exitMenu();
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
                    break;
            }
        } while (choice != 5);
    } 
    public void viewAndManageHospitalStaff() {
        Scanner scanner = new Scanner(System.in);
        int choice;

        do {
            System.out.println("\nView and Manage Hospital Staff:");
            System.out.println("1. View All Staff");
            System.out.println("2. Add Staff");
            System.out.println("3. Update Staff");
            System.out.println("4. Remove Staff");
            System.out.println("5. Filter Staff");
            System.out.println("6. Go Back");

            choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    viewAllStaff();
                    break;
                case 2:
                    addStaff(scanner);
                    break;
                case 3:
                    updateStaff(scanner);
                    break;
                case 4:
                    removeStaff(scanner);
                    break;
                case 5:
                    filterStaff(scanner);
                    break;
                case 6:
                    System.out.println("Returning to Main Menu...");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        } while (choice != 6);
    }

    private void viewAllStaff() {
        List<staff> staffList = staffManager.getStaffList();
        if (staffList.isEmpty()) {
            System.out.println("No staff members found.");
        } else {
            System.out.println("Hospital Staff:");
            for (staff s : staffList) {
                System.out.println(s);
            }
        }
    }

    private void addStaff(Scanner scanner) {
        System.out.print("Enter Staff ID: ");
        String staffID = scanner.nextLine();
        System.out.print("Enter Name: ");
        String name = scanner.nextLine();
        System.out.print("Enter Role: ");
        String role = scanner.nextLine();
        System.out.print("Enter Gender: ");
        String gender = scanner.nextLine();
        System.out.print("Enter Age: ");
        String age = scanner.nextLine();

        staffManager.addStaff(staffID, name, role, gender, age);
    }

    private void updateStaff(Scanner scanner) {
        System.out.print("Enter Staff ID to Update: ");
        String staffID = scanner.nextLine();
        System.out.print("Enter New Name: ");
        String name = scanner.nextLine();
        System.out.print("Enter New Role: ");
        String role = scanner.nextLine();
        System.out.print("Enter New Gender: ");
        String gender = scanner.nextLine();
        System.out.print("Enter New Age: ");
        String age = scanner.nextLine();

        staffManager.updateStaff(staffID, name, role, gender, age);
    }

    private void removeStaff(Scanner scanner) {
        System.out.print("Enter Staff ID to Remove: ");
        String staffID = scanner.nextLine();

        staffManager.removeStaff(staffID);
    }

    private void filterStaff(Scanner scanner) {
        System.out.print("Enter Filter Type (role/gender/age): ");
        String filterType = scanner.nextLine();
        System.out.print("Enter Filter Value: ");
        String filterValue = scanner.nextLine();

        List<staff> filteredStaff = staffManager.filterStaff(filterType, filterValue);
        if (filteredStaff.isEmpty()) {
            System.out.println("No staff members match the filter criteria.");
        } else {
            System.out.println("Filtered Staff:");
            for (staff s : filteredStaff) {
                System.out.println(s);
            }
        }
    }
    
    private void approveReplenishmentRequest(Scanner scanner) {
        if (inventory.getReplenishmentRequests().isEmpty()) {
            System.out.println("No replenishment requests to approve.");
            return;
        }
    
        System.out.println("Replenishment Requests:");
        inventory.viewReplenishmentRequests();  
        System.out.print("Enter the Medicine Name to approve replenishment: ");
        String medicineName = scanner.nextLine();
    
        if (!inventory.getReplenishmentRequests().containsKey(medicineName)) {
            System.err.println("Error: No replenishment request found for \"" + medicineName + "\".");
            return;
        }
        int quantity = inventory.getReplenishmentRequests().get(medicineName);
        inventory.updateStockLevel(medicineName, quantity);
        inventory.getReplenishmentRequests().remove(medicineName);
    
        System.out.println("Replenishment request approved for \"" + medicineName + "\". Stock updated by " + quantity + " units.");    
        
    }

public void viewAndManageMedicationInventory() {
    Scanner scanner = new Scanner(System.in);
    int choice;
    System.out.println("1. View Medication Inventory");
    for (Medication medication : inventory.getMedicationList()) {
        System.out.println(medication);
    }
    do {
        System.out.println("\nManage Medication Inventory:");    
        System.out.println("1. Add Medication");
        System.out.println("2. Remove Medication");
        System.out.println("3. Update Stock Level");
        System.out.println("4. Update Low Stock Alert");
        System.out.println("5. View Medication Inventory");
        System.out.println("6. Go Back");

        choice = scanner.nextInt();
            scanner.nextLine();
        switch(choice){
            case 1:
            System.out.print("Enter Medicine Name: ");
                    String nameToAdd = scanner.nextLine();
                    System.out.print("Enter Initial Stock: ");
                    int stockToAdd = scanner.nextInt();
                    System.out.print("Enter Low Stock Alert Level: ");
                    int alertToAdd = scanner.nextInt();
                    inventory.addMedication(nameToAdd, stockToAdd, alertToAdd);
                    break;
            case 2: 
            System.out.print("Enter Medicine Name to Remove: ");
                    String nameToRemove = scanner.nextLine();
                    inventory.removeMedication(nameToRemove);
                    break;
            case 3: 
            System.out.print("Enter Medicine Name to Update Stock Level: ");
            String nameToUpdateStock = scanner.nextLine();
            System.out.print("Enter New Stock Level: ");
            int newStock = scanner.nextInt();
            inventory.updateStockLevel(nameToUpdateStock, newStock);
            break;
            case 4:
            System.out.print("Enter Medicine Name to Update Low Stock Alert: ");
            String nameToUpdateAlert = scanner.nextLine();
            System.out.print("Enter New Low Stock Alert Level: ");
            int newAlert = scanner.nextInt();
            inventory.updateLowStockAlert(nameToUpdateAlert, newAlert);
            break;
            case 5: 
            inventory.viewMedicationInventory();
            break;
            case 6: 
            System.out.println("Returning to Main Menu...");
            break;
            default:
            System.out.println("Invalid choice. Please try again.");
        }
        }while (choice !=6);
    }  
    private void viewAppointmentDetails() {
        if (appointmentManager == null) {
            System.err.println("Error: AppointmentManager is not initialized.");
            return;
        }
    
        List<String> appointmentDetails = appointmentManager.getAllScheduledAppointments();
        if (appointmentDetails.isEmpty()) {
            System.out.println("No appointments found.");
        } else {
            System.out.println("Scheduled Appointments:");
            for (String details : appointmentDetails) {
                System.out.println(details);
            }
        }
}    
}
