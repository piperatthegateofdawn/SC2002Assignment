package user.menu;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

import Appointment.PatientApptService;
import MedicalRecord.MedicalRecord;
import user.patient;


public class patientMenu extends UserMenu{
    private patient patient;
    private MedicalRecord medicalRecord;
     private PatientApptService patientApptService;

    public patient getPatient() {
        return patient;
    }

    public patientMenu(user.patient patient,PatientApptService patientApptService) {
        this.patient = patient;
        this.medicalRecord = new MedicalRecord(patient);
        this.patientApptService = patientApptService;
    }
@Override
public void DisplayMenu(){

    Scanner scanner = new Scanner(System.in);
    int choice = -1;

    do {
    System.out.println("1. View Medical Record");
    System.out.println("2. Update Personal Information");
    System.out.println("3. View Available Appointment Slots");
    System.out.println("4. Schedule an Appointment");
    System.out.println("5. Reschedule an Appointment");
    System.out.println("6. Cancel an Appointment");
    System.out.println("7. View Scheduled Appointments");
    System.out.println("8. View Past Appointment Outcome Records");
    System.out.println("9. Logout");

//Exception handling incase a String is entered instead of an integer
    choice = getUserChoice();
    switch (choice) {
        case 1:
           medicalRecord.viewMedicalRecord();
          break;
        case 2:
        System.out.print("Enter new contact information: ");
        String newContactInfo = scanner.nextLine();
        patient.updateContactInfo(newContactInfo);
          break;
        case 3:
        viewAvailableSlots(scanner);
          break;
        case 4:
        scheduleAppointment(scanner);
          break;
        case 5:
        rescheduleAppointment(scanner);
          break;
        case 6:
        cancelAppointment(scanner);
          break;
        case 7:
        viewScheduledAppointments();
          break;
        case 8:
        viewPastAppointments();
          break;
        case 9:
        exitMenu();
          break; 
        default:
          System.out.println("Invalid choice. Please try again.");  

    }


   }while(choice !=9);
}


private void viewAvailableSlots(Scanner scanner) {
  System.out.print("Enter Doctor ID to view available slots: ");
  String doctorId = scanner.nextLine();
  patientApptService.viewAvailableSlots(doctorId);
}

private void scheduleAppointment(Scanner scanner) {
System.out.print("Enter Doctor ID for the appointment: ");
String doctorId = scanner.nextLine().trim();

// Validate Doctor ID
if (doctorId.isEmpty()) {
    System.out.println("Error: Doctor ID cannot be empty.");
    return;
}

// Show available slots for the doctor
patientApptService.viewAvailableSlots(doctorId);

String date;
while (true) {
    System.out.print("Enter appointment date (e.g., '2024-11-15'): ");
    date = scanner.nextLine().trim();
    
    // Validate date format
    if (!date.matches("\\d{4}-\\d{2}-\\d{2}")) {
        System.out.println("Error: Invalid date format. Expected format: YYYY-MM-DD.");
        continue;
    }

    LocalDate appointmentDate = LocalDate.parse(date);

    // Validate if date is in the past
    if (appointmentDate.isBefore(LocalDate.now())) {
        System.out.println("Error: Appointment date cannot be in the past.");
        continue;
    }

    // Validate if date is more than one week ahead
    if (appointmentDate.isAfter(LocalDate.now().plusWeeks(1))) {
        System.out.println("Error: Appointment date cannot be more than one week ahead.");
        continue;
    }

    // Validate if the appointment is on a Sunday
    if (appointmentDate.getDayOfWeek() == DayOfWeek.SUNDAY) {
        System.out.println("Error: Appointment cannot be scheduled on a Sunday.");
        continue;
    }

    break; // Date is valid
}

String time;
while (true) {
    System.out.print("Enter appointment time (e.g., '10:00'): ");
    time = scanner.nextLine().trim();
    
    // Validate time format
    if (!time.matches("\\d{2}:\\d{2}")) {
        System.out.println("Error: Invalid time format. Expected format: HH:mm.");
        continue;
    }

    LocalTime appointmentTime;
    try {
        appointmentTime = LocalTime.parse(time);
    } catch (DateTimeParseException e) {
        System.out.println("Error: Invalid time format. Please use HH:mm.");
        continue;
    }

    // Validate if time is within 9 AM to 6 PM
    if (appointmentTime.isBefore(LocalTime.of(9, 0)) || appointmentTime.isAfter(LocalTime.of(17, 30))) {
        System.out.println("Error: Appointment time must be between 09:00 and 17:30.");
        continue;
    }

    break; // Time is valid
}

// Attempt to schedule the appointment
boolean success = patientApptService.scheduleAppointment(patient.getPatientID(), doctorId, date, time);
if (success) {
    System.out.println("Appointment scheduled successfully.");
} else {
    System.out.println("Failed to schedule appointment. Please try another slot.");
}

}

private void rescheduleAppointment(Scanner scanner) {
  System.out.print("Enter the ID of the appointment you'd like to reschedule: ");
  String appointmentId = scanner.nextLine();
  System.out.print("Enter the new date (e.g., '2024-11-16'): ");
  String newDate = scanner.nextLine();
  System.out.print("Enter the new time (e.g., '11:00'): ");
  String newTime = scanner.nextLine();
  boolean success = patientApptService.rescheduleAppointment(appointmentId, newDate, newTime);
  if (success) {
      System.out.println("Appointment rescheduled successfully.");
  } else {
      System.out.println("Failed to reschedule appointment. Please try again.");
  }
}

private void cancelAppointment(Scanner scanner) {
  System.out.print("Enter the ID of the appointment you'd like to cancel: ");
  String appointmentId = scanner.nextLine();
  patientApptService.cancelAppointment(appointmentId);
}

private void viewScheduledAppointments() {
  System.out.println("Scheduled Appointments:");
  patientApptService.viewScheduledAppointments(patient.getPatientID());
}

private void viewPastAppointments() {
  System.out.println("Past Appointments and Outcomes:");
  patientApptService.viewPastAppointmentsWithOutcomes(patient.getPatientID());
}
}


