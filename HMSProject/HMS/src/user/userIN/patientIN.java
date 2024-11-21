
package user.userIN;
/* 
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import user.patient;

public class patientIN {
    private static final String PATIENT_PATH = "data"+File.separator+"Patient_List.csv";
    private List<patient> patientList = new ArrayList<>();
      

    public void loadPatientList() {
        try (BufferedReader br = new BufferedReader(new FileReader(PATIENT_PATH))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                if (values.length == 6) {
                   
                    patient patient= new patient(values[0], values[1], values[2],values[3],values[4],values[5]);
                    patientList.add(patient);
                    
                }
            }
            System.out.println("Patient list imported successfully.");
        } catch (IOException e) {
            System.err.println("Error reading patient list: " + e.getMessage());
        }
       }
public List<patient> getPatientList() {
    return patientList;
}
public void setPatientList(List<patient> patientList) {
    this.patientList = patientList;
}
}
*/

import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import user.patient;
import java.io.File;

public class patientIN {
    private static final String PATIENT_PATH = "data" + File.separator + "Patient_List.csv";
    private List<patient> patientList = new ArrayList<>();

    /**
     * Load patient list from the CSV file.
     */
    public void loadPatientList() {
        try (BufferedReader br = new BufferedReader(new FileReader(PATIENT_PATH))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                if (values.length == 6) {
                    patient patient = new patient(values[0], values[1], values[2], values[3], values[4], values[5]);
                    patientList.add(patient);
                }
            }
            System.out.println("Patient list imported successfully.");
        } catch (IOException e) {
            System.err.println("Error reading patient list: " + e.getMessage());
        }
    }

    /**
     * Add a new patient to the list and save to the CSV file.
     */
    public void addPatient(patient newPatient) {
        patientList.add(newPatient);
        try (FileWriter writer = new FileWriter(PATIENT_PATH, true)) {
            writer.write(String.join(",", 
                newPatient.getPatientID(),
                newPatient.getName(),
                newPatient.getDateOfBirth(),
                newPatient.getGender(),
                newPatient.getBloodType(),
                newPatient.getContactInfo()) + "\n");
            System.out.println("New patient added successfully: " + newPatient.getName());
        } catch (IOException e) {
            System.err.println("Error saving new patient: " + e.getMessage());
        }
    }

    public List<patient> getPatientList() {
        return patientList;
    }

    public void setPatientList(List<patient> patientList) {
        this.patientList = patientList;
    }
}
