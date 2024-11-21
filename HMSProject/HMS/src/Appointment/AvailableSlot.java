package Appointment;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import Appointment.PatientApptService;

//import java.util.List;

public class AvailableSlot {
    private String doctorId;
    private String date;  // format: DD-MM-YYYY
    private String time;  // format: 09:00 (24-hr CLOCK)

    public AvailableSlot(String doctorId, String date, String time) {
        this.doctorId = doctorId;
        this.date = date;
        this.time = time;
    }

    // Getters
    public String getDoctorId() {
        return doctorId;
    }

    public String getDate() {
        return date;
    }

    public String getTime() {
        return time;
    }

    @Override
    public String toString() {  //convert all details into one string to store in list
        return "Doctor ID: " + doctorId + ", Date: " + date + ", Time: " + time;  
    }
    public static void viewAvailableSlots(List<AvailableSlot> slots, String doctorId) {
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
    }
    
}
