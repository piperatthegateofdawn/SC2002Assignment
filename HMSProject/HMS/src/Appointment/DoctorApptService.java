package Appointment;

import java.util.List;

public class DoctorApptService {
    private AppointmentManager appointmentManager;

    public DoctorApptService(AppointmentManager appointmentManager) {
        this.appointmentManager = appointmentManager;
    }

    // View personal schedule
    public void viewPersonalSchedule(String doctorId) {
        List<Appointment> schedule = appointmentManager.viewDoctorSchedule(doctorId);
        if (schedule.isEmpty()) {
            System.out.println("========================================");
            System.out.println("       NO SCHEDULED APPOINTMENTS        ");
            System.out.println("========================================");
        } else {
            System.out.println("========================================");
            System.out.println("         SCHEDULED APPOINTMENTS         ");
            System.out.println("========================================");
            System.out.printf("%-15s | %-15s | %-10s | %-5s | %-10s%n", 
                              "Appointment ID", "Patient ID", "Date", "Time", "Status");
            System.out.println("----------------------------------------");
        
            for (Appointment app : schedule) {
                System.out.printf("%-15s | %-15s | %-10s | %-5s | %-10s%n", 
                                  app.getAppointmentId(), app.getPatientId(), 
                                  app.getDate(), app.getTime(), app.getStatus());
            }
        
            System.out.println("========================================");
        }
        
    }

    

    // Accept or decline an appointment request
    public boolean acceptOrDeclineAppointment(String appointmentId, boolean accept) {
        boolean result = appointmentManager.acceptOrDeclineAppointment(appointmentId, accept);
        if (result) {
            if (accept) {
                System.out.println("Appointment " + appointmentId + " has been accepted.");
            } else {
                System.out.println("Appointment " + appointmentId + " has been declined.");
            }
        } else {
            System.out.println("Failed to find appointment with ID: " + appointmentId);
        }
        return result;
    }

    // View upcoming appointments
    public void viewUpcomingAppointments(String doctorId) {
        List<Appointment> upcomingAppointments = appointmentManager.viewUpcomingAppointments(doctorId);
        if (upcomingAppointments.isEmpty()) {
            System.out.println("No upcoming appointments.");
        } else {
            for (Appointment app : upcomingAppointments) {
                System.out.println("Appointment ID: " + app.getAppointmentId() + ", Patient ID: " + app.getPatientId() +
                ", Date: " + app.getDate() + ", Time: " + app.getTime() + ", Status: " + app.getStatus());
            }
        }
    }

    // Record the outcome of a patient appointment
    public boolean recordAppointmentOutcome(String appointmentId, String dateOfAppointment, String serviceType,
                                            String consultationNotes, List<Medication> medications) {
        boolean result = appointmentManager.recordAppointmentOutcome(appointmentId, dateOfAppointment, serviceType, consultationNotes, medications);
        if (result) {
            System.out.println("Outcome recorded for appointment ID: " + appointmentId);
            System.out.println("Service Type: " + serviceType);
            System.out.println("Consultation Notes: " + consultationNotes);
            System.out.println("Prescribed Medications:");
            for (Medication med : medications) {
                System.out.println(" - " + med.getName() + " (Status: " + med.getStatus() + ")");
            }
        } else {
            System.out.println("Failed to record outcome for appointment ID: " + appointmentId);
        }
        return result;
    }
}
