package user;

import java.util.regex.Pattern;
import MedicalRecord.MedicalRecord;

public class patient {
    private String patientID;
    private String name;
    private String dateOfBirth;
    private String gender;
    private String contactInfo;
    private String bloodType;
    private MedicalRecord medicalRecord;



    public patient (String patientID, String name, String dateOfBirth, String gender, String bloodType,String contactInfo) {
       
        this.patientID = patientID;
        this.name = name;
        this.dateOfBirth = dateOfBirth;
        this.gender = gender;
        this.bloodType = bloodType;
        this.contactInfo = contactInfo;
        this.medicalRecord = new MedicalRecord(this);

    }



    public MedicalRecord getMedicalRecord() {
        return medicalRecord;
    }



    public String getPatientID() {
        return patientID;
    }



    public void setPatientID(String patientID) {
        this.patientID = patientID;
    }



    public String getName() {
        return name;
    }



    public void setName(String name) {
        this.name = name;
    }



    public String getDateOfBirth() {
        return dateOfBirth;
    }



    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }



    public String getGender() {
        return gender;
    }



    public void setGender(String gender) {
        this.gender = gender;
    }



    public String getContactInfo() {
        return contactInfo;
    }



    public void setContactInfo(String contactInfo) {
        this.contactInfo = contactInfo;
    }



    public String getBloodType() {
        return bloodType;
    }



    public void setBloodType(String bloodType) {
        this.bloodType = bloodType;
    }

    public void updateContactInfo(String newContactInfo) {
        // Regular expression for validating an email address
        String emailRegex = "^[\\w.%+-]+@[\\w.-]+\\.[a-zA-Z]{2,}$";
        Pattern emailPattern = Pattern.compile(emailRegex);
    
        // Regular expression for validating an 8-digit number starting with 8 or 9
        String phoneRegex = "^[89]\\d{7}$";
        Pattern phonePattern = Pattern.compile(phoneRegex);
    
        if (emailPattern.matcher(newContactInfo).matches()) {
            // Valid email address
            this.contactInfo = newContactInfo;
            System.out.println("Contact information updated successfully (Email).");
        } else if (phonePattern.matcher(newContactInfo).matches()) {
            // Valid phone number
            this.contactInfo = newContactInfo;
            System.out.println("Contact information updated successfully (Phone Number).");
        } else {
            // Invalid input
            System.out.println("Invalid contact information. Please provide a valid email or an 8-digit phone number starting with 8 or 9.");
        }
    }
    

    @Override
    public String toString() {
        return "Patient ID: " + patientID + ", Name: " + name + ", Date of Birth: " + dateOfBirth + 
               ", Gender: " + gender + ", Contact Info: " + contactInfo + ", Blood Type: " + bloodType;
    }
}
