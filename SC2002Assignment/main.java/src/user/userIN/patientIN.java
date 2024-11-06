package user.userIN;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import user.patient;

public class patientIN {
    private static final String PATIENT_PATH = "D:/SC2002Assignment/data/Patient_List.csv";
    private List<patient> patientList = new ArrayList<>();
      
    /**
     * 
     */
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
