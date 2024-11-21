package Appointment;

import java.util.ArrayList;
import java.util.List;

public class AppointmentOutcome {
    private String appointmentId;
    private String dateOfAppointment;
    private String serviceType; // e.g., consultation, X-ray, blood test, etc.
    private List<Medication> prescribedMedications; // List to store medications
    private String consultationNotes;

    // Constructor
    public AppointmentOutcome(String appointmentId, String dateOfAppointment, String serviceType, String consultationNotes) {
        this.appointmentId = appointmentId;
        this.dateOfAppointment = dateOfAppointment;
        this.serviceType = serviceType;
        this.consultationNotes = consultationNotes;
        this.prescribedMedications = new ArrayList<>();
    }

    // Getters and Setters
    public String getAppointmentId() {
        return appointmentId;
    }

    public String getDateOfAppointment() {
        return dateOfAppointment;
    }

    public String getServiceType() {
        return serviceType;
    }

    public void setServiceType(String serviceType) {
        this.serviceType = serviceType;
    }

    public List<Medication> getPrescribedMedications() {
        return prescribedMedications;
    }

    public void addMedication(Medication medication) {
        this.prescribedMedications.add(medication);
    }

    public String getConsultationNotes() {
        return consultationNotes;
    }

    public void setConsultationNotes(String consultationNotes) {
        this.consultationNotes = consultationNotes;
    }
}

