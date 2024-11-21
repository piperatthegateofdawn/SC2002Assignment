package user.menu;

import java.util.InputMismatchException;
import java.util.Scanner;

import user.user;

public abstract class UserMenu {
    protected Scanner scanner;

    public UserMenu() {
        this.scanner = new Scanner(System.in);
    }

    
    public abstract void DisplayMenu();

    protected int getUserChoice() {
        int choice = -1;
        try {
            choice = scanner.nextInt();
            scanner.nextLine(); 
        } catch (InputMismatchException e) {
            System.out.println("Invalid input. Please enter a valid number.");
            scanner.nextLine(); 
        }
        return choice;
    }
    
    protected void exitMenu() {
        System.out.println("Logging out...Goodbye!");
    }

    

}
