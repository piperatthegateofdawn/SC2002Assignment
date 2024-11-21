package Appointment;

public class Medication {
    private String name;
    private String status; // eg: pending, dispensed

    // Constructor
    public Medication(String name) {
        this.name = name;
        this.status = "pending"; // default status is pending
    }

    // Getters and Setters
    public String getName() {
        return name;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}

