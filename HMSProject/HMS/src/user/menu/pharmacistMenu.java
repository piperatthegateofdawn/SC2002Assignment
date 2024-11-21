package user.menu;

import Inventory.Inventory;
import Inventory.Medication;
import Appointment.PharmApptService;
import Inventory.Inter_Inventory;


import java.util.Scanner;

public class pharmacistMenu extends UserMenu{
    private Inter_Inventory inventory;
    private Scanner scanner;
    private PharmApptService pharmApptService;

    public pharmacistMenu() {
        this.inventory = new Inventory();
        this.inventory.loadMedicationList();
   }

   public pharmacistMenu(Inter_Inventory inventory, PharmApptService pharmApptService) {
        this.inventory = inventory;
        this.pharmApptService = pharmApptService;
        this.scanner = new Scanner(System.in); 
    }
@Override
    public void DisplayMenu() {
        int choice = -1;

        do {
            System.out.println("\nPharmacist Menu:");
            System.out.println("1. View Appoitment Outcome Record");
            System.out.println("2. Update Prescription Status");
            System.out.println("3. View Medication Inventory");
            System.out.println("4. Submit Replenishment Request");
            System.out.println("5.Log out");


            choice = getUserChoice();

            switch (choice) {
                case 1:
                    viewAppointmentOutconeRecord();
                    break;
                case 2:
                    updatePrescriptionStatus();
                    break;
                case 3:
                if (inventory != null) {
                        if (inventory.getMedicationList().isEmpty()) {
                            System.out.println("No medication in the inventory.");
                        } else {
                            for (Medication medication : inventory.getMedicationList()) {
                                System.out.println(medication);
                            }
                        }
                    } else {
                        System.err.println("Error: Inventory is not initialized.");
                    }
                    break;
                case 4:
                    submitReplenishmentRequest(scanner);
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

    
    private void submitReplenishmentRequest(Scanner scanner) {
        System.out.print("Enter Medication for replenishment: ");
        String medicationName = scanner.nextLine();
        System.out.print("Enter quantity to replenish: ");
        int quantity = scanner.nextInt();
        inventory.submitReplenishmentRequest(medicationName, quantity);
    }
    private void viewAppointmentOutconeRecord(){
        if (pharmApptService == null) {
            System.err.println("Error: Appointment service is not initialized.");
            return;
        }
        System.out.println("Select the Appointment ID of the Outconme Record you want to view:");
        String appointmentId = scanner.nextLine();
        pharmApptService.viewAppointmentOutcome(appointmentId);

    }
    private void updatePrescriptionStatus() {
        if (pharmApptService == null) {
            System.err.println("Error: Appointment service is not initialized.");
            return;
        }
    
        System.out.println("Enter the Appointment ID for which you want to update the prescription status:");
        String appointmentId = scanner.nextLine();
    
        System.out.println("Enter the name of the medication:");
        String medicationName = scanner.nextLine();
    
        System.out.println("Enter the new status for the medication (e.g., 'Dispensed', 'Pending'):");
        String newStatus = scanner.nextLine();
    
        boolean success = pharmApptService.updateMedicationStatus(appointmentId, medicationName, newStatus);
    
        if (success) {
            System.out.println("Prescription status updated successfully.");
        } else {
            System.err.println("Failed to update prescription status. Check if the appointment ID or medication name is correct.");
        }
    }
    
}
