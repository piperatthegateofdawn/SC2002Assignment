
import java.util.Scanner;
import java.util.concurrent.TimeUnit;
import java.util.Random;

import user.patient;
import user.staff;
import user.user;
import Inventory.Inventory;
import user.userIN.patientIN;
import user.userIN.staffIN;
import user.menu.administratorMenu;
import user.menu.doctorMenu;
import user.menu.patientMenu;
import user.menu.pharmacistMenu;
import Appointment.AppointmentManager;
import Appointment.PatientApptService;
import Appointment.PharmApptService;
import Appointment.AdministratorApptService;
import Inventory.Inter_Inventory;




public class Initialize {
 
    private boolean isInitialized = false;
    private boolean Running = false;

   
    private patientIN patientLoader = new patientIN();
    private staffIN staffLoader = new staffIN();
    private Inter_Inventory inventory = new Inventory();

    private user userSystem;
    private pharmacistMenu pharmacistMenu;
    private patientMenu patientMenu;
    private doctorMenu doctorMenu;
    private administratorMenu administratorMenu;

    
    
  public void initialize(){
    if (isInitialized) return;

    //add initialization code
    staffLoader.loadStaffList();
    System.out.println(staffLoader.getStaffList());
    patientLoader.loadPatientList();
    System.out.println(patientLoader.getPatientList());
    inventory.loadMedicationList();
    System.out.println(inventory.getMedicationList());
    userSystem = new user(patientLoader.getPatientList(), staffLoader.getStaffList());
    isInitialized = true;
  }  
  
public void run() {
    if (!isInitialized){
        throw new IllegalStateException("HMS not initialized");
    }

    if (Running) return;
    AppointmentManager appointmentManager = new AppointmentManager();
    PharmApptService pharmApptService = new PharmApptService(appointmentManager);
    AdministratorApptService administratorApptService = new AdministratorApptService(appointmentManager);
    TwoFactorAuth twoFactorAuth = new TwoFactorAuth();
    Running = true;
    while (Running) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please log in:");
        System.out.println("Enter your ID:");
        String userId = scanner.nextLine();

        System.out.println("Enter your password:");
        String password = scanner.nextLine();
        
        
        if (userSystem.login(userId, password)) {
           
            if (password.equals("password")) {
                System.out.println("You are using the default password. For security reasons, you must change your password.");
                while (true) {
                    System.out.println("Enter your new password:");
                    String newPassword = scanner.nextLine();

                    System.out.println("Confirm your new password:");
                    String confirmPassword = scanner.nextLine();

                    if (!newPassword.equals(confirmPassword)) {
                        System.out.println("Passwords do not match. Please try again.");
                    } else if (newPassword.trim().isEmpty()) {
                        System.out.println("Password cannot be empty. Please try again.");
                    } else {
                        userSystem.setPassword(userId, newPassword);
                        System.out.println("Password successfully updated. Please log in again with your new password.");
                        break;
                    }
                }
                continue; 
            }
        }

            //2FA OTP Verification
            twoFactorAuth.generateOtp();
            System.out.println("An OTP has been sent to your contact information. (Simulated: " + twoFactorAuth.getGeneratedOtp() + ")");
            boolean otpVerified = false;

            for (int attempts = 0; attempts < 3; attempts++) { // Allow up to 3 attempts
                System.out.println("Enter the OTP:");
                String enteredOtp = scanner.nextLine();

                if (twoFactorAuth.verifyOtp(enteredOtp)) {
                    otpVerified = true;
                    break;
                } else {
                    System.out.println("Invalid or expired OTP. Please try again.");
                }
            }

            if (!otpVerified) {
                System.out.println("Too many failed attempts. Please try logging in again.");
                continue;
            }

            System.out.println("OTP verified successfully!");

            
            String userType = userSystem.getUserTypeMap().get(userId);

                
            switch (userType) {
                case "patient":
                   System.out.println("Opening Patient Menu...");
                   patient patientInstance = userSystem.getPatientById(userId); 
                   PatientApptService patientApptService = new PatientApptService(appointmentManager); 
                    patientMenu = new patientMenu(patientInstance, patientApptService);
                   patientMenu.DisplayMenu();
                    break;
                case "staff":
                    // Logic to open specific staff menus based on user ID prefix
                    if (userId.startsWith("D")) {
                        System.out.println("Opening Doctor Menu...");
                        staff doctorInstance = userSystem.getStaffById(userId);
                        doctorMenu = new doctorMenu(doctorInstance,patientLoader, appointmentManager);
                        doctorMenu.DisplayMenu();
                    } else if (userId.startsWith("P")) {
                        System.out.println("Opening Pharmacist Menu...");
                        pharmacistMenu = new pharmacistMenu(inventory,pharmApptService);
                        pharmacistMenu.DisplayMenu();
                    } else if (userId.startsWith("A")) {
                        System.out.println("Opening Administrator Menu...");
                        administratorMenu = new administratorMenu(inventory,staffLoader,appointmentManager);

                        administratorMenu.DisplayMenu();
                    } else {
                        System.out.println("Unknown staff type. Unable to open menu.");
                    }
                    break;
                default:
                    System.out.println("Unknown user type. Unable to open menu.");
            } 
        } 
}
}
                    
    
  


