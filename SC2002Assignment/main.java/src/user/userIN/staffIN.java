package user.userIN;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import user.staff;

public class staffIN {
    private static final String STAFF_PATH = "D:/SC2002Assignment/data/Staff_List.csv";
    private List<staff> staffList = new ArrayList<>();

      
    /**
     * 
     */
    public void loadStaffList() {
        try (BufferedReader br = new BufferedReader(new FileReader(STAFF_PATH))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                if (values.length == 5) {
                   
                    staff staff= new staff(values[0], values[1], values[2],values[3],values[4]);
                    staffList.add(staff);
                    
                }
            }
            System.out.println("Staff list imported successfully.");
        } catch (IOException e) {
            System.err.println("Error reading patient list: " + e.getMessage());
        }
       }
public List<staff> getStaffList() {
    return staffList;
}
public void setStaffList(List<staff> staffList) {
    this.staffList = staffList;
}
}
