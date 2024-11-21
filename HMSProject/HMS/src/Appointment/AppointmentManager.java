package Appointment;

import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.time.*;

//contains all appointment-related functions for the 4 users handling appointments

public class AppointmentManager {
    private List<Appointment> appointmentList = new ArrayList<>();  
    //list to store all scheduled appointments (Appointment class)
    private List<AvailableSlot> availableSlots = new ArrayList<>();
    //list to store doctors (doctorId) available slots (Date and time)
    private Map<String, AppointmentOutcome> appointmentOutcomes = new HashMap<>(); 
    // Stores outcomes by appointmentId

    public AppointmentManager() {
       //availableSlots.add(new AvailableSlot("D001", "15-11-2024", "09:00"));
    }

    // Method to get all scheduled appointments
    public List<Appointment> getAllAppointments() {
        return new ArrayList<>(appointmentList); // Return a copy to prevent modification
    }

    // Method to get all available slots
    public List<AvailableSlot> getAvailableSlots() {
        return availableSlots; 
    }    
// PATIENT METHODS
    // Method to schedule an appointment
    public boolean scheduleAppointment(String patientId, String doctorId, String date, String time) {
        for (AvailableSlot slot : availableSlots) {
            if (slot.getDoctorId().equals(doctorId) && slot.getDate().equals(date) && slot.getTime().equals(time)) {
                String appointmentId = generateAppointmentId();
                Appointment appointment = new Appointment(appointmentId, patientId, doctorId, date, time, "pending");
                appointmentList.add(appointment);
                availableSlots.remove(slot); // Remove the slot once booked
                return true;
            }
        }
        return false; // No matching slot found
    }

 public boolean rescheduleAppointment(String appointmentId, String newDate, String newTime) {
        Optional<Appointment> appointmentOpt = appointmentList.stream()
                .filter(app -> app.getAppointmentId().equals(appointmentId))
                .findFirst();
        if (appointmentOpt.isPresent()) {
            Appointment appointment = appointmentOpt.get();
            String doctorId = appointment.getDoctorId();

            // Check if new slot is available
            for (AvailableSlot slot : availableSlots) {
                if (slot.getDoctorId().equals(doctorId) && slot.getDate().equals(newDate) && slot.getTime().equals(newTime)) {
                    // Make old slot available again
                    availableSlots.add(new AvailableSlot(doctorId, appointment.getDate(), appointment.getTime()));

                    // Reschedule appointment
                    appointment.setDate(newDate);
                    appointment.setTime(newTime);
                    availableSlots.remove(slot); // Remove new slot from availability
                    return true;
                }
            }
        }
        return false; // No matching appointment or new slot unavailable
    }

    public boolean cancelAppointment(String appointmentId) {
        Optional<Appointment> appointmentOpt = appointmentList.stream()
                .filter(app -> app.getAppointmentId().equals(appointmentId))
                .findFirst();
        if (appointmentOpt.isPresent()) {
            Appointment appointment = appointmentOpt.get();
            String doctorId = appointment.getDoctorId();
            availableSlots.add(new AvailableSlot(doctorId, appointment.getDate(), appointment.getTime())); // Make slot available again
            appointmentList.remove(appointment);
            return true;
        }
        return false; // Appointment not found
    }

    //Patient can view past appointment outcomes
    public List<String> getPatientPastAppointmentsWithOutcomes(String patientId) {
        List<String> pastAppointments = new ArrayList<>();
        for (Appointment appointment : appointmentList) {
            if (appointment.getPatientId().equals(patientId) && "completed".equals(appointment.getStatus())) {
                StringBuilder details = new StringBuilder();
                details.append("Appointment ID: ").append(appointment.getAppointmentId())
                       .append(", Doctor ID: ").append(appointment.getDoctorId())
                       .append(", Date: ").append(appointment.getDate())
                       .append(", Time: ").append(appointment.getTime());

                // Fetch and include outcome details
                Optional<AppointmentOutcome> outcomeOpt = getAppointmentOutcome(appointment.getAppointmentId());
                outcomeOpt.ifPresent(outcome -> {
                    details.append(", Outcome Details: {")
                           .append("Service Type: ").append(outcome.getServiceType())
                           .append(", Consultation Notes: ").append(outcome.getConsultationNotes())
                           .append(", Medications: ");
                    for (Medication med : outcome.getPrescribedMedications()) {
                        details.append(med.getName()).append(" (Status: ").append(med.getStatus()).append("), ");
                    }
                    // Remove trailing comma and space, if any
                    if (!outcome.getPrescribedMedications().isEmpty()) {
                        details.setLength(details.length() - 2);
                    }
                    details.append("}");
                });

                pastAppointments.add(details.toString());
            }
        }
        return pastAppointments;
    }

    // Method to generate a unique appointment ID 
    private String generateAppointmentId() {
        return "APT-" + (appointmentList.size() + 1);
    }

    //Add to list
    public void addAvailableSlot(String doctorId, String date, String time) {
        availableSlots.add(new AvailableSlot(doctorId, date, time));
    }

//DOCTOR METHODS
public Optional<Appointment> getAppointmentById(String appointmentId) {
    return appointmentList.stream()
            .filter(app -> app.getAppointmentId().equalsIgnoreCase(appointmentId))
            .findFirst();
}

    //Set Availabililty by breaking down time range into 30-minute slots

public void setAvailability(String doctorId, AppointmentManager appointmentManager) {
    Scanner scanner = new Scanner(System.in);
    DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm");

    try {
        // Collect date input
        System.out.print("Enter Date (YYYY-MM-DD): ");
        String date = scanner.nextLine();
        if (!date.matches("\\d{4}-\\d{2}-\\d{2}")) {
            throw new IllegalArgumentException("Invalid date format. Expected format: YYYY-MM-DD.");
        }

        LocalDate appointmentDate = LocalDate.parse(date);

        // Check for invalid dates (e.g., past dates, unexpected months, Sundays, and more)
        if (appointmentDate.isBefore(LocalDate.now())) {
            throw new IllegalArgumentException("Booking in the past is not allowed.");
        }
        if (appointmentDate.isAfter(LocalDate.now().plusWeeks(1))) {
            throw new IllegalArgumentException("Booking can only be done up to one week in advance.");
        }
        if (appointmentDate.getMonthValue() < 1 || appointmentDate.getMonthValue() > 12) {
            throw new IllegalArgumentException("Unexpected month. Valid months are from 1 to 12.");
        }
        if (appointmentDate.getDayOfWeek() == DayOfWeek.SUNDAY) {
            throw new IllegalArgumentException("Booking on Sundays is not allowed.");
        }

        // Collect start time input
        System.out.print("Enter Start Time (HH:mm) 24-HOUR CLOCK: ");
        String startTimeInput = scanner.nextLine();
        LocalTime startTime = parseTime(startTimeInput, timeFormatter);

        // Collect end time input
        System.out.print("Enter End Time (HH:mm) 24-HOUR CLOCK: ");
        String endTimeInput = scanner.nextLine();
        LocalTime endTime = parseTime(endTimeInput, timeFormatter);

        // Validate time range
        if (startTime.isAfter(endTime) || startTime.equals(endTime)) {
            throw new IllegalArgumentException("Start time must be before end time.");
        }
        if (startTime.isBefore(LocalTime.of(9, 0)) || endTime.isAfter(LocalTime.of(18, 0))) {
            throw new IllegalArgumentException("Availability timings must be between 09:00 and 18:00.");
        }

        // Clear previous slots for the doctor on the same date
        appointmentManager.getAvailableSlots().removeIf(slot ->
                slot.getDoctorId().equals(doctorId) && slot.getDate().equals(date));

        // Set availability in 30-minute slots
        LocalTime current = startTime;
        while (current.isBefore(endTime)) {
            LocalTime nextSlot = current.plusMinutes(30);

            // Ensure slots fit within the provided range
            if (nextSlot.isAfter(endTime)) {
                break;
            }

            // Create a final variable for the current time to use inside the lambda
            final String slotTime = current.toString();

            // Avoid adding duplicate slots
            boolean exists = appointmentManager.getAvailableSlots().stream()
                    .anyMatch(slot -> slot.getDoctorId().equals(doctorId) &&
                            slot.getDate().equals(date) &&
                            slot.getTime().equals(slotTime));
            if (!exists) {
                // Add the slot to availability
                appointmentManager.getAvailableSlots().add(new AvailableSlot(doctorId, date, slotTime));
            }

            current = nextSlot;
        }

        // Display success message and available slots
        System.out.println("Availability successfully set for " + doctorId + " on " + date);
        AvailableSlot.viewAvailableSlots(appointmentManager.getAvailableSlots(), doctorId);

    } catch (IllegalArgumentException e) {
        System.out.println("Error: " + e.getMessage());
    } catch (DateTimeParseException e) {
        System.out.println("Error: Invalid time format. Please use HH:mm format.");
    } catch (Exception e) {
        System.out.println("An unexpected error occurred: " + e.getMessage());
    }
}

    
    // Helper method to parse time with error handling
    private LocalTime parseTime(String timeInput, DateTimeFormatter formatter) {
        try {
            return LocalTime.parse(timeInput, formatter);
        } catch (DateTimeParseException e) {
            throw new IllegalArgumentException("Invalid time format. Expected format: HH:mm.");
        }
    }
    
     // View a doctor's personal schedule
     public List<Appointment> viewDoctorSchedule(String doctorId) {
        return appointmentList.stream()
                .filter(app -> app.getDoctorId().equals(doctorId))
                .collect(Collectors.toList());
    }
    // Accept or decline an appointment request
    public boolean acceptOrDeclineAppointment(String appointmentId, boolean accept) {
        Optional<Appointment> appointmentOpt = appointmentList.stream()
                .filter(app -> app.getAppointmentId().equals(appointmentId))
                .findFirst();
        if (appointmentOpt.isPresent()) {
            Appointment appointment = appointmentOpt.get();
            if (accept) {
                appointment.setStatus("confirmed");
            } else {
                appointment.setStatus("declined");
                // Make slot available again if appointment is declined
                availableSlots.add(new AvailableSlot(appointment.getDoctorId(), appointment.getDate(), appointment.getTime()));
                appointmentList.remove(appointment);
            }
            return true;
        }
        return false; // Appointment not found
    }

    // View upcoming appointments for a doctor
    public List<Appointment> viewUpcomingAppointments(String doctorId) {
        return appointmentList.stream()
                .filter(app -> app.getDoctorId().equals(doctorId) && app.getStatus().equals("confirmed"))
                .collect(Collectors.toList());
    }

    // Record the outcome of a patient appointment
    public boolean recordAppointmentOutcome(String appointmentId, String dateOfAppointment, String serviceType,
                                            String consultationNotes, List<Medication> medications) {
        Optional<Appointment> appointmentOpt = appointmentList.stream()
                .filter(app -> app.getAppointmentId().equals(appointmentId))
                .findFirst();
        if (appointmentOpt.isPresent()) {
            Appointment appointment = appointmentOpt.get();
            appointment.setStatus("completed");

            AppointmentOutcome outcome = new AppointmentOutcome(appointmentId, dateOfAppointment, serviceType, consultationNotes);
            for (Medication med : medications) {
                outcome.addMedication(med);
            }
            appointmentOutcomes.put(appointmentId, outcome); // Store the outcome
            return true;
        }
        return false; // Appointment not found
    }

    public Optional<AppointmentOutcome> getAppointmentOutcome(String appointmentId) {
        return Optional.ofNullable(appointmentOutcomes.get(appointmentId));
    }

//ADMINISTRATOR METHODS
 // get all scheduled appointments with their details
 public List<String> getAllScheduledAppointments() {
    List<String> appointmentDetails = new ArrayList<>();
    for (Appointment appointment : appointmentList) {
        StringBuilder details = new StringBuilder();
        details.append("Appointment ID: ").append(appointment.getAppointmentId())
               .append(", Patient ID: ").append(appointment.getPatientId())
               .append(", Doctor ID: ").append(appointment.getDoctorId())
               .append(", Status: ").append(appointment.getStatus())
               .append(", Date: ").append(appointment.getDate())
               .append(", Time: ").append(appointment.getTime());

        // If appointment is completed, include the outcome details
        if ("completed".equals(appointment.getStatus())) {
            Optional<AppointmentOutcome> outcomeOpt = getAppointmentOutcome(appointment.getAppointmentId());
            outcomeOpt.ifPresent(outcome -> {
                details.append(", Outcome Details: {")
                       .append("Service Type: ").append(outcome.getServiceType())
                       .append(", Consultation Notes: ").append(outcome.getConsultationNotes())
                       .append(", Medications: ");
                for (Medication med : outcome.getPrescribedMedications()) {
                    details.append(med.getName()).append(" (Status: ").append(med.getStatus()).append("), ");
                }
                // Remove trailing comma and space, if any
                if (!outcome.getPrescribedMedications().isEmpty()) {
                    details.setLength(details.length() - 2);
                }
                details.append("}");
            });
        }

        appointmentDetails.add(details.toString());
    }
    return appointmentDetails;
}
}



