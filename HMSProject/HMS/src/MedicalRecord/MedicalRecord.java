package MedicalRecord;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import user.patient;

public class MedicalRecord {
    private patient patient;
    private List<String> diagnoses = new ArrayList<>();
    private List<String> treatments = new ArrayList<>();
    private List<String> prescriptions = new ArrayList<>();
 
    

    public MedicalRecord(patient patient) {
        this.patient = patient;
        this.diagnoses = new ArrayList<>();
        this.treatments = new ArrayList<>();
        this.prescriptions = new ArrayList<>();

        initializeDefaults();
    }

    private void initializeDefaults() {
        if (diagnoses.isEmpty()) {
            diagnoses.add("healthy");
        }
        if (treatments.isEmpty()) {
            treatments.add("none");
        }
        if (prescriptions.isEmpty()) {
            prescriptions.add("nothing");
        }

    }

    public patient getPatient() {
        return patient;
    }

    public List<String> getDiagnoses() {
        return diagnoses;
    }

    public List<String> getTreatments() {
        return treatments;
    }
    public List<String> getPrescriptions() {
        return prescriptions;
    }

    public void viewMedicalRecord() {
        System.out.println("========================================");
        System.out.println("            MEDICAL RECORD              ");
        System.out.println("========================================");
        System.out.printf("%-20s: %s%n", "Patient ID", patient.getPatientID());
        System.out.printf("%-20s: %s%n", "Name", patient.getName());
        System.out.printf("%-20s: %s%n", "Date of Birth", patient.getDateOfBirth());
        System.out.printf("%-20s: %s%n", "Gender", patient.getGender());
        System.out.printf("%-20s: %s%n", "Contact Information", patient.getContactInfo());
        System.out.printf("%-20s: %s%n", "Blood Type", patient.getBloodType());
        System.out.println("----------------------------------------");
        System.out.println("Past Diagnoses:");
        System.out.println(getDiagnoses());
        System.out.println("----------------------------------------");
        System.out.println("Treatments:");
        System.out.println(getTreatments());
        System.out.println("----------------------------------------");
        System.out.println("Prescriptions:");
        System.out.println(getPrescriptions());
        System.out.println("========================================");
    }
    
    public void updateMedicalRecord(){
        Scanner scanner = new Scanner(System.in);
        int choice;

        do {
            System.out.println("\nSelect what to update:");
            System.out.println("1.Add/Update Diagnoses");
            System.out.println("2. Update Treatments");
            System.out.println("3. Update Prescriptions");
            System.out.println("4. Exit");

            choice = scanner.nextInt();
            scanner.nextLine();  // Consume newline character

            switch (choice) {
                case 1:
                switch (choice) {
                    case 1:
                        System.out.println("Choose an option:");
                        System.out.println("1. Add a new diagnosis");
                        System.out.println("2. Modify an existing diagnosis");
                        int option = scanner.nextInt();
                        scanner.nextLine();  // Consume newline character
                        
                        if (option == 1) {
                            // Add a new diagnosis
                            System.out.println("Enter new diagnosis (or type 'done' to stop):");
                            String diagnosis;
                            while (!(diagnosis = scanner.nextLine()).equalsIgnoreCase("done")) {
                                if (!diagnosis.trim().isEmpty()) {
                                    diagnoses.add(diagnosis);  // Add the new diagnosis
                                    System.out.println("Diagnosis added.");
                                    break;
                                } else {
                                    System.out.println("Please enter a valid diagnosis.");
                                    break;
                                }
                            }
                        } else if (option == 2) {
                            // Modify an existing diagnosis
                            if (diagnoses.isEmpty()) {
                                System.out.println("No diagnoses to modify.");
                            } else {
                                System.out.println("Existing Diagnoses:");
                                for (int i = 0; i < diagnoses.size(); i++) {
                                    System.out.println((i + 1) + ". " + diagnoses.get(i));
                                }
        
                                System.out.println("Enter the number of the diagnosis you want to modify:");
                                int diagnosisIndex = scanner.nextInt() - 1;  // Convert to 0-based index
                                scanner.nextLine();  // Consume newline character
        
                                if (diagnosisIndex >= 0 && diagnosisIndex < diagnoses.size()) {
                                    System.out.println("Enter new diagnosis:");
                                    String newDiagnosis = scanner.nextLine();
                                    if (!newDiagnosis.trim().isEmpty()) {
                                        diagnoses.set(diagnosisIndex, newDiagnosis);  // Replace the diagnosis
                                        System.out.println("Diagnosis updated.");
                                    } else {
                                        System.out.println("Invalid input. Diagnosis not updated.");
                                    }
                                } else {
                                    System.out.println("Invalid diagnosis number.");
                                }
                            }
                        }
                        break;
                }
                case 2:
                    System.out.println("Enter new treatddonwment (or type 'done' to stop):");
                    String treatment;
                    while (!(treatment = scanner.nextLine()).equalsIgnoreCase("done")) {
                        if (!treatment.trim().isEmpty()) {
                            treatments.add(treatment);  // Add the treatment
                            System.out.println("Treatment added.");
                        } else {
                            System.out.println("Please enter a valid treatment.");
                        }
                    }
                    break;

                case 3:
                    System.out.println("Enter new prescription (or type 'done' to stop):");
                    String prescription;
                    while (!(prescription = scanner.nextLine()).equalsIgnoreCase("done")) {
                           if (!prescription.trim().isEmpty()) {
                            prescriptions.add(prescription);  // Add the prescription
                            System.out.println("Prescription added.");
                        } else {
                            System.out.println("Please enter a valid prescription.");
                        }
                    }
                    break;

                case 4:
                    System.out.println("Exiting update menu...");
                    break;

                default:
                    System.out.println("Invalid choice. Please try again.");
                    break;
                }
        }while(choice!=4);


    }
    
}

