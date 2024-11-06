import java.io.BufferedReader;
import java.io.FileReader;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import user.user;
import user.userIN.patientIN;
import user.userIN.staffIN;

public class Initialize {
 
    private boolean isInitialized = false;
    private boolean Running = false;

   
    private patientIN patientLoader = new patientIN();
    private staffIN staffLoader = new staffIN();
    private user userSystem;


  public void initialize(){
    if (isInitialized) return;

    //add initialization code
    staffLoader.loadStaffList();
    System.out.println(staffLoader.getStaffList());
    patientLoader.loadPatientList();
    System.out.println(patientLoader.getPatientList());

    userSystem = new user(patientLoader.getPatientList(), staffLoader.getStaffList());
    isInitialized = true;
  }  
  
public void run() {
    if (!isInitialized){
        throw new IllegalStateException("HMS not initialized");
    }

    if (Running) return;

    Running = true;
    while (Running) {
        Scanner scanner = new Scanner(System.in);
        
        
        System.out.println("Please log in: ");
        System.out.println("Please enter your ID:");
        String userId = scanner.nextLine();
        
        System.out.println("Please enter your password:");
        String password = scanner.nextLine();
     
        userSystem.login(userId,password);



        
    }
    
}
}

