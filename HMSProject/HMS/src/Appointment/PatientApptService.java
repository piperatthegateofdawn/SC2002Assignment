package Appointment;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class PatientApptService {
    private AppointmentManager patientAppt;

//constructor
    public PatientApptService(AppointmentManager patientAppt){
        this.patientAppt = patientAppt;
    }

    //can only see timings, appointments made by another patient should not be visible
    //patient would first select the doctor they want then view their availability

public void viewAvailableSlots(String doctorId) {
    AvailableSlot.viewAvailableSlots(patientAppt.getAvailableSlots(), doctorId);

    /* 
    List<AvailableSlot> slots = patientAppt.getAvailableSlots();
    if (slots.isEmpty()) {
        System.out.println("No available slots found.");
        return;
    }

    // Filter slots for the specified doctor
    List<AvailableSlot> doctorSlots = slots.stream()
            .filter(slot -> slot.getDoctorId().equalsIgnoreCase(doctorId.trim()))
            .collect(Collectors.toList());

    if (doctorSlots.isEmpty()) {
        System.out.println("No available slots found for Doctor ID: " + doctorId);
        return;
    }

    // Display available slots grouped by date
    System.out.println("Available slots for Doctor ID: " + doctorId);
    System.out.println("========================================");

    // Group slots by date
    Map<String, List<AvailableSlot>> slotsByDate = doctorSlots.stream()
            .collect(Collectors.groupingBy(AvailableSlot::getDate));

    for (Map.Entry<String, List<AvailableSlot>> entry : slotsByDate.entrySet()) {
        String date = entry.getKey();
        List<AvailableSlot> dateSlots = entry.getValue();
        
        System.out.println("Date: " + date);
        System.out.println("----------------------------");
        for (AvailableSlot slot : dateSlots) {
            System.out.println(" - Time: " + slot.getTime());
        }
        System.out.println();
    }
    */
}


    // Method for patient to schedule an appointment - uses scheduleAppointment() method from AppointmentManager class (encapsulation)
    public boolean scheduleAppointment(String patientId, String doctorId, String date, String time) {
        boolean success = patientAppt.scheduleAppointment(patientId, doctorId, date, time);
        if (success) {
            System.out.println("Appointment scheduled successfully (Confirmation staus: pending)");
            return true;
        } else {
            System.out.println("Failed to schedule appointment. Slot not available.");
            return false;
        }
    }
       // Reschedule an appointment
       public boolean rescheduleAppointment(String appointmentId, String newDate, String newTime) {
        boolean success = patientAppt.rescheduleAppointment(appointmentId, newDate, newTime);
        if (success) {
            System.out.println("Appointment rescheduled successfully.");
            return true;
        } else {

            return false;
        }
    }

    // Cancel an appointment
    public boolean cancelAppointment(String appointmentId) {
        boolean success = patientAppt.cancelAppointment(appointmentId);
        if (success) {
            System.out.println("Appointment canceled successfully.");
            return true;
        } else {
            System.out.println("Failed to cancel appointment. Appointment not found.");
            return false;
        }
    }

    // View scheduled appointments and their status
// View scheduled appointments and their status
public void viewScheduledAppointments(String patientId) {
    List<Appointment> appointments = patientAppt.getAllAppointments().stream()
            .filter(appointment -> appointment.getPatientId().equals(patientId))
            .collect(Collectors.toList());

    if (appointments.isEmpty()) {
        System.out.println("========================================");
        System.out.println("       NO SCHEDULED APPOINTMENTS        ");
        System.out.println("========================================");
        return;
    }

    System.out.println("========================================");
    System.out.println("       SCHEDULED APPOINTMENTS           ");
    System.out.println("========================================");
    System.out.printf("%-15s | %-10s | %-10s | %-5s | %-10s%n", 
                      "Appointment ID", "Doctor ID", "Date", "Time", "Status");
    System.out.println("----------------------------------------");

    for (Appointment appointment : appointments) {
        System.out.printf("%-15s | %-10s | %-10s | %-5s | %-10s%n", 
                          appointment.getAppointmentId(), 
                          appointment.getDoctorId(), 
                          appointment.getDate(), 
                          appointment.getTime(), 
                          appointment.getStatus());
    }
    System.out.println("========================================");
}


      // View past completed appointments with outcomes
    public void viewPastAppointmentsWithOutcomes(String patientId) {
        List<String> pastAppointments = patientAppt.getPatientPastAppointmentsWithOutcomes(patientId);
        if (pastAppointments.isEmpty()) {
            System.out.println("No past completed appointments found.");
        } else {
            for (String details : pastAppointments) {
                System.out.println(details);
            }
        }
    }
}
