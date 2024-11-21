package user.userIN;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import user.staff;

public class staffIN {
    private static final String STAFF_PATH = "data"+File.separator+"Staff_List.csv";
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
            System.err.println("Error reading staff list: " + e.getMessage());
        }
       }
public List<staff> getStaffList() {
    return staffList;
}
public void setStaffList(List<staff> staffList) {
    this.staffList = staffList;
} public void addStaff(String staffID, String name, String role, String gender, String age) {
        for (staff s : staffList) {
            if (s.getStaffID().equalsIgnoreCase(staffID)) {
                System.err.println("Error: Staff with ID \"" + staffID + "\" already exists.");
                return;
            }
        }
        staff newStaff = new staff(staffID, name, role, gender, age);
        staffList.add(newStaff);
        System.out.println("Staff \"" + name + "\" added successfully.");
    }

    // 更新员工信息
    public void updateStaff(String staffID, String name, String role, String gender, String age) {
        for (staff s : staffList) {
            if (s.getStaffID().equalsIgnoreCase(staffID)) {
                s.setName(name);
                s.setRole(role);
                s.setGender(gender);
                s.setAge(age);
                System.out.println("Staff with ID \"" + staffID + "\" updated successfully.");
                return;
            }
        }
        System.err.println("Error: Staff with ID \"" + staffID + "\" does not exist.");
    }

    // 删除员工
    public void removeStaff(String staffID) {
        for (staff s : staffList) {
            if (s.getStaffID().equalsIgnoreCase(staffID)) {
                staffList.remove(s);
                System.out.println("Staff with ID \"" + staffID + "\" removed successfully.");
                return;
            }
        }
        System.err.println("Error: Staff with ID \"" + staffID + "\" does not exist.");
    }

    // 根据条件过滤员工列表
    public List<staff> filterStaff(String filterType, String filterValue) {
        switch (filterType.toLowerCase()) {
            case "role":
                return staffList.stream()
                        .filter(s -> s.getRole().equalsIgnoreCase(filterValue))
                        .collect(Collectors.toList());
            case "gender":
                return staffList.stream()
                        .filter(s -> s.getGender().equalsIgnoreCase(filterValue))
                        .collect(Collectors.toList());
            case "age":
                return staffList.stream()
                        .filter(s -> s.getAge().equals(filterValue))
                        .collect(Collectors.toList());
            default:
                System.err.println("Error: Invalid filter type. Use 'role', 'gender', or 'age'.");
                return new ArrayList<>();
        }
    }
}
