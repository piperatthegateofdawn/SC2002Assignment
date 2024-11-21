package user;
/* 
import java.util.HashMap;
import java.util.List;



public class user {
    private HashMap<String,String> userMap;
    private HashMap<String, String> userTypeMap; 
    private HashMap<String, patient> patientMap;
    private HashMap<String, staff> staffMap;
    
   
    public HashMap<String, String> getUserTypeMap() {
        return userTypeMap;
    }
    public void setUserTypeMap(HashMap<String, String> userTypeMap) {
        this.userTypeMap = userTypeMap;
    }
    public HashMap<String, String> getUserMap() {
        return userMap;
    }
    public void setUserMap(HashMap<String, String> userMap) {
        this.userMap = userMap;
    }

    public user(List<patient> patientList, List<staff> staffList) {
        userMap = new HashMap<>();
        userTypeMap = new HashMap<>();
        patientMap = new HashMap<>(); 
        staffMap = new HashMap<>();

        for (patient patient : patientList) {
            String patientId = patient.getPatientID();
            userMap.put(patientId, "password"); 
            userTypeMap.put(patientId, "patient");
            patientMap.put(patientId, patient);

            
        }
        
        // 将 staffList 中的 staffId 加入 HashMap
        for (staff staff : staffList) {
            String staffId = staff.getStaffID();
            userMap.put(staffId, "password"); 
            userTypeMap.put(staffId, "staff");
            staffMap.put(staffId, staff);
        }
    }
    public patient getPatientById(String userId) {
        return patientMap.get(userId);  // 通过 userId 获取相应的 patient 对象
    }
    public staff getStaffById(String userId) {
        return staffMap.get(userId); 
    }

    public boolean login(String userId, String password) {
        
        if (userExists(userId) && userMap.get(userId).equals(password)) {
            System.out.println("Login successful. Welcome, " + userId + "!");
            return true;

        } else {
            //System.out.println("Login failed. Please check your user ID and password.");
            return false;
        }
    }
    public boolean userExists(String userId) {
        return userMap.containsKey(userId);
    }
    public String getPassword(String userId) {
        return userMap.get(userId);
    }
    public void setPassword(String userId, String newPassword) {
        if (userExists(userId)) {
            userMap.put(userId, newPassword);
        } else {
            System.out.println("User ID not found.");
        }
    }    
        
    }
*/

import java.util.HashMap;
import java.util.List;

public class user {
    private HashMap<String, String> userMap; // Stores userID and password
    private HashMap<String, String> userTypeMap; // Stores userID and type (patient/staff)
    private HashMap<String, patient> patientMap; // Stores userID and patient object
    private HashMap<String, staff> staffMap; // Stores userID and staff object

    public user(List<patient> patientList, List<staff> staffList) {
        userMap = new HashMap<>();
        userTypeMap = new HashMap<>();
        patientMap = new HashMap<>();
        staffMap = new HashMap<>();

        for (patient patient : patientList) {
            String patientId = patient.getPatientID();
            userMap.put(patientId, "password"); // Default password
            userTypeMap.put(patientId, "patient");
            patientMap.put(patientId, patient);
        }

        for (staff staff : staffList) {
            String staffId = staff.getStaffID();
            userMap.put(staffId, "password"); // Default password
            userTypeMap.put(staffId, "staff");
            staffMap.put(staffId, staff);
        }
    }

    public HashMap<String, String> getUserTypeMap() {
        return userTypeMap;
    }

    public void setUserTypeMap(HashMap<String, String> userTypeMap) {
        this.userTypeMap = userTypeMap;
    }

    public HashMap<String, String> getUserMap() {
        return userMap;
    }

    public void setUserMap(HashMap<String, String> userMap) {
        this.userMap = userMap;
    }

    public patient getPatientById(String userId) {
        return patientMap.get(userId); // Retrieve patient by userID
    }

    public staff getStaffById(String userId) {
        return staffMap.get(userId); // Retrieve staff by userID
    }

    /**
     * Check if a user exists in the system.
     */
    public boolean userExists(String userId) {
        return userMap.containsKey(userId);
    }

    /**
     * Add a new patient user with a default password.
     */
    public void addPatientUser(String patientID, String defaultPassword) {
        userMap.put(patientID, defaultPassword);
        userTypeMap.put(patientID, "patient");
        System.out.println("New patient user added: " + patientID);
    }

    /**
     * Update a user's password.
     */
    public void setPassword(String userId, String newPassword) {
        if (userExists(userId)) {
            userMap.put(userId, newPassword);
            System.out.println("Password updated successfully for user: " + userId);
        } else {
            System.out.println("User ID not found.");
        }
    }

    /**
     * Authenticate user login by ID and password.
     */
    public boolean login(String userId, String password) {
        if (userExists(userId) && userMap.get(userId).equals(password)) {
            System.out.println("Login successful. Welcome, " + userId + "!");
            return true;
        } else {
         //   System.out.println("Login failed. Please check your user ID and password.");
            return false;
        }
    }
}
