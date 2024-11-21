package Appointment;
import java.util.List;

public class AdministratorApptService {
    private AppointmentManager appointmentManager;

    public AdministratorApptService(AppointmentManager appointmentManager) {
        this.appointmentManager = appointmentManager;
    }

    // View all scheduled appointments with their details
    public void viewAllScheduledAppointments() {
        List<String> appointments = appointmentManager.getAllScheduledAppointments();
        if (appointments.isEmpty()) {
            System.out.println("No scheduled appointments found.");
        } else {
            for (String details : appointments) {
                System.out.println(details);
            }
        }
    }
}

