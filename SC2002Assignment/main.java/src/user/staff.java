package user;

public class staff {
    private String staffID;
    private String name;
    private String role;
    private String gender;
    private String age;

    public staff(String staffID, String name, String role, String gender, String age) {
        this.staffID = staffID;
        this.name = name;
        this.role = role;
        this.gender = gender;
        this.age = age;
    }

    public String getStaffID() {
        return staffID;
    }

    public void setStaffID(String staffID) {
        this.staffID = staffID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }
    @Override
    public String toString() {
        return "Staff ID: " + staffID + ", Name: " + name + ", Role: " + role+", Gender: "+gender+", Age: "+age;
    }
   
}

