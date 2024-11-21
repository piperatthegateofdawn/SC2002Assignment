package user.menu;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;
import java.util.stream.Collectors;

import MedicalRecord.MedicalRecord;
import user.patient;
import user.staff;
import user.userIN.patientIN;
import Appointment.Appointment;
import Appointment.AppointmentManager;
import Appointment.AppointmentOutcome;
import Appointment.Medication;
import Appointment.AvailableSlot;
import Appointment.DoctorApptService;

public class doctorMenu extends UserMenu{
   private MedicalRecord medicalRecord;
   private staff doctor;
   public doctorMenu(staff doctor) {
      this.doctor = doctor;
   }
   private patientIN patientLoader;
   private DoctorApptService doctorService; // Service for handling doctor-specific appointment operations
   private AppointmentManager appointmentManager; // Manager to handle appointments
    public doctorMenu(staff doctor, patientIN patientLoader, AppointmentManager appointmentManager) {
       this.doctor = doctor;
       this.patientLoader = patientLoader;  // Initialize patientLoader
       this.appointmentManager = appointmentManager;
       this.doctorService = new DoctorApptService(appointmentManager);
   }
 @Override
   public void DisplayMenu(){

    Scanner scanner = new Scanner(System.in);
    int choice = -1;
 
    do{
    System.out.println("1. View Patient Medical Record");
    System.out.println("2. Update Patient Medical Records");
    System.out.println("3. View Personal Schedule");
    System.out.println("4. Set Availability for Appointments");
    System.out.println("5. Accept or Decline Appointment Requests ");
    System.out.println("6. View Upcoming Appointments ");
    System.out.println("7. Record Appointment Outcome");
    System.out.println("8. Logout ");
    

    choice = getUserChoice();

    switch (choice) {
      case 1:
        System.out.println("Enter the Patient ID of the patient you want to view:");
        String id = scanner.nextLine();

         boolean found = false;
         for (patient p : patientLoader.getPatientList()) {
            if (p.getPatientID().equals(id)) {
                MedicalRecord medicalRecord = p.getMedicalRecord();
                medicalRecord.viewMedicalRecord(); 
                found = true;
                break;
            }
        }

      if (!found) {
            System.out.println("No record found for Patient ID: " + id);
        }
             break;
      case 2:
      System.out.println("Enter the Patient ID of the patient you want to view:");
    String id2 = scanner.nextLine();

    boolean found2 = false;
    for (patient p : patientLoader.getPatientList()) {
        if (p.getPatientID().equals(id2)) {
            MedicalRecord medicalRecord = p.getMedicalRecord();
            medicalRecord.updateMedicalRecord(); 
            found2 = true;
            break; // Exit the loop once a match is found
        }


    // Display message only if no record was found after the loop
         else if (!found2) {
        System.out.println("No record found for Patient ID: " + id2);
    }
}
    break;


      case 3:
      doctorService.viewPersonalSchedule(doctor.getStaffID());
        break;
      case 4:
      try {
          
     appointmentManager.setAvailability(doctor.getStaffID(), appointmentManager);
  
      } catch (Exception e) {
          // Catch unexpected exceptions to prevent crashes
          System.out.println("An unexpected error occurred while setting availability: " + e.getMessage());
      }
      break;
  
      case 5:System.out.println("Pending appointment requests for you:");
      List<Appointment> pendingAppointments = appointmentManager.viewDoctorSchedule(doctor.getStaffID())
          .stream()
          .filter(app -> "pending".equals(app.getStatus())) // Filter for pending appointments
          .collect(Collectors.toList());
      
      if (pendingAppointments.isEmpty()) {
          System.out.println("You have no pending appointment requests.");
      } else {
          for (Appointment app : pendingAppointments) {
              System.out.println("Appointment ID: " + app.getAppointmentId() + ", Patient ID: " + app.getPatientId() +
                      ", Date: " + app.getDate() + ", Time: " + app.getTime() + ", Status: " + app.getStatus());
          }

          // Proceed to accept or decline
          System.out.print("Enter the Appointment ID to accept or decline: ");
          String appointmentId = scanner.nextLine();
          System.out.print("Do you want to accept the appointment? (yes/no): ");
          String response = scanner.nextLine().trim().toLowerCase();
          boolean accept = response.equals("yes");
          doctorService.acceptOrDeclineAppointment(appointmentId, accept);
      }
        break;
      case 6:
      doctorService.viewUpcomingAppointments(doctor.getStaffID());
        break;
      case 7:
    System.out.println("Enter the Appointment ID for recording an outcome:");
    String appointmentIdOutcome = scanner.nextLine().trim();

    // Validate Appointment ID
    Optional<Appointment> appointmentOptional = appointmentManager.getAppointmentById(appointmentIdOutcome);
    if (appointmentOptional == null) {
        System.out.println("Error: Appointment ID not found. Please check and try again.");
        break;
    }

    Appointment appointment = appointmentOptional.get();
    String dateOfAppointment = appointment.getDate(); // Fetch date directly from appointment record

    System.out.println("Appointment Date: " + dateOfAppointment); // Inform the doctor of the fetched date
    System.out.println("Enter the type of service provided:");
    String serviceType = scanner.nextLine().trim();
    if (serviceType.isEmpty()) {
        System.out.println("Error: Service type cannot be empty.");
        break;
    }

    System.out.println("Enter consultation notes:");
    String consultationNotes = scanner.nextLine().trim();
    if (consultationNotes.isEmpty()) {
        System.out.println("Error: Consultation notes cannot be empty.");
        break;
    }

    AppointmentOutcome outcome = new AppointmentOutcome(appointmentIdOutcome, dateOfAppointment, serviceType, consultationNotes);

    System.out.println("Enter prescribed medications (type 'done' when finished):");
    while (true) {
        System.out.print("Medication Name: ");
        String medicationName = scanner.nextLine().trim();
        if ("done".equalsIgnoreCase(medicationName)) {
            break;
        }
        if (medicationName.isEmpty()) {
            System.out.println("Error: Medication name cannot be empty.");
            continue;
        }
        Medication medication = new Medication(medicationName);
        outcome.addMedication(medication); // Add medication to the outcome record
    }

    // Call doctor service to record the outcome
    boolean result = doctorService.recordAppointmentOutcome(
        outcome.getAppointmentId(),
        outcome.getDateOfAppointment(),
        outcome.getServiceType(),
        outcome.getConsultationNotes(),
        outcome.getPrescribedMedications()
    );

    if (result) {
        System.out.println("Outcome successfully recorded.");
    } else {
        System.out.println("Failed to record the outcome. Please try again.");
    }
    break;

      case 8:
      exitMenu();
      break; 
      case 9:
      default:
        System.out.println("Invalid choice. Please try again.");  

  }
   } while (choice != 8); 

}
}