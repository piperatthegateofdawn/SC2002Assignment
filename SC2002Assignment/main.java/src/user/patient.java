package user;

public class patient {
    private String patientID;
    private String name;
    private String dateOfBirth;
    private String gender;
    private String contactInfo;
    private String bloodType;



    public patient (String patientID, String name, String dateOfBirth, String gender, String contactInfo,String bloodType) {
       
        this.patientID = patientID;
        this.name = name;
        this.dateOfBirth = dateOfBirth;
        this.gender = gender;
        this.contactInfo = contactInfo;
        this.bloodType = bloodType;

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

    @Override
    public String toString() {
        return "Patient ID: " + patientID + ", Name: " + name + ", Date of Birth: " + dateOfBirth + 
               ", Gender: " + gender + ", Contact Info: " + contactInfo + ", Blood Type: " + bloodType;
    }
}
