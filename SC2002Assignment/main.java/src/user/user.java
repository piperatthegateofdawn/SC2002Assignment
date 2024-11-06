package user;
import java.util.HashMap;
import java.util.List;


public class user {
    private HashMap<String,String> userMap;
    public user(List<patient> patientList, List<staff> staffList) {
        userMap = new HashMap<>();
        for (patient patient : patientList) {
            String patientId = patient.getPatientID();
            userMap.put(patientId, "password"); 
        }
        
        // 将 staffList 中的 staffId 加入 HashMap
        for (staff staff : staffList) {
            String staffId = staff.getStaffID();
            userMap.put(staffId, "password"); 
        }
    }
    public boolean login(String userId, String password) {
        System.out.println(userId+password);
        if (userExists(userId) && userMap.get(userId).equals(password)) {
            System.out.println("Login successful. Welcome, " + userId + "!");
            return true;
        } else {
            System.out.println("Login failed. Please check your user ID and password.");
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
    
    
