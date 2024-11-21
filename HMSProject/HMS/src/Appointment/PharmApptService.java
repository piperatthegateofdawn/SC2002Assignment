package Appointment;

import java.util.Optional;

public class PharmApptService {
    private AppointmentManager appointmentManager;

    public PharmApptService(AppointmentManager appointmentManager) {
        this.appointmentManager = appointmentManager;
    }

    // View appointment outcome records
    public void viewAppointmentOutcome(String appointmentId) {
        Optional<AppointmentOutcome> outcomeOpt = appointmentManager.getAppointmentOutcome(appointmentId);
        if (outcomeOpt.isPresent()) {
            AppointmentOutcome outcome = outcomeOpt.get();
            System.out.println("Appointment ID: " + outcome.getAppointmentId());
            System.out.println("Date: " + outcome.getDateOfAppointment());
            System.out.println("Service: " + outcome.getServiceType());
            System.out.println("Consultation Notes: " + outcome.getConsultationNotes());
            System.out.println("Medications:");
            for (Medication med : outcome.getPrescribedMedications()) {
                System.out.println(" - " + med.getName() + " (Status: " + med.getStatus() + ")");
            }
        } else {
            System.out.println("No outcome found for appointment ID: " + appointmentId);
        }
    }

    // Update the status of a medication
    public boolean updateMedicationStatus(String appointmentId, String medicationName, String newStatus) {
        Optional<AppointmentOutcome> outcomeOpt = appointmentManager.getAppointmentOutcome(appointmentId);
        if (outcomeOpt.isPresent()) {
            AppointmentOutcome outcome = outcomeOpt.get();
            for (Medication med : outcome.getPrescribedMedications()) {
                if (med.getName().equals(medicationName)) {
                    med.setStatus(newStatus);
                    System.out.println("Updated status of medication '" + medicationName + "' to " + newStatus);
                    return true;
                }
            }
            System.out.println("Medication not found: " + medicationName);
        } else {
            System.out.println("No outcome found for appointment ID: " + appointmentId);
        }
        return false;
    }
}

