package Appointment;
import java.util.ArrayList;
import java.util.List;

public class Appointment {
    private String appointmentId;
    private String patientId;
    private String doctorId;
    private String date;  //format: DD-MM-YYYY
    private String time;  //format: 09:00 (24-hr CLOCK)
    private String status;  //pending,confirmed(by doctor), declined (by doctor), cancelled(by patient)
    private List<String> outcomes;  //added by doctor

    public Appointment(String appointmentId, String patientId, String doctorId, String date, String time, String status) {
        this.appointmentId = appointmentId;
        this.patientId = patientId;
        this.doctorId = doctorId;
        this.date = date;
        this.time = time;
        this.status = status;
        this.outcomes = new ArrayList<>();
    }

    // Getters and Setters
    public String getAppointmentId() {
        return appointmentId;
    }
    public String getPatientId() {
        return patientId;
    }
    public String getDoctorId() {
        return doctorId;
    }
    public String getDate() {
        return date;
    }
    public String getTime() {
        return time;
    }
    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }
    public void setDate(String date){
        this.date = date;
    }
    public void setTime(String time){
        this.time = time;
    }
    public List<String> getOutcomes(){
        return new ArrayList<>(outcomes);
    }
    public void addOutcome(String outcome){
        this.outcomes.add(outcome);
    }
}