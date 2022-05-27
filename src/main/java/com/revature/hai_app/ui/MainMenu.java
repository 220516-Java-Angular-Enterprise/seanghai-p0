package com.revature.hai_app.ui;

import com.revature.hai_app.daos.userDAO;
import com.revature.hai_app.models.User;
import com.revature.hai_app.services.UserService;

import java.awt.*;
import java.util.*;

public class MainMenu implements MenuTemplate{
    // Dependency
    private final User user;
    public MainMenu(User user){this.user=user;}
    UserService userService = new UserService(new userDAO());
    @Override
    public void start(){
        System.out.println("Welcome to the main menu " + user.getUsername());
        displayMainMenu();

    }

    private void displayMainMenu(){
        System.out.println("[1] Browse by class/role");
        System.out.println("[2] View all items");
        System.out.println("[3] Search");
        System.out.println("[4] View Order History");
        System.out.println("[5] View account information");
        System.out.println("[6] Log out");

        Scanner scan = new Scanner(System.in);
        System.out.println("\nEnter: ");
        String input = scan.nextLine();
        switch (input) {
            case "1":
                System.out.println("Browsing...");
                break;
            case "2":
                System.out.println("Viewing...");
                break;
            case "3":
                System.out.println("Searching...");
                break;
            case "4":
                System.out.println("Viewing Order History");
                break;
            case "5":
                displayAccountInfo();
                break;
            case "6":
                new StartMenu(userService).start();
            default:
                System.out.println("Wrong input.");
                break;
        }
    }

    private void displayAccountInfo(){
        System.out.println("Your account information is listed below. \n");
        System.out.println("Username: " + user.getUsername());
        System.out.println("Email: " + user.getEmail());
        System.out.println("Store Credits: " + user.getStorecredits());
        System.out.println("\n[1] Change password");
        System.out.println("[2] Change email");
        System.out.println("[3] Add Store Credits");
        System.out.println("[4] Back to Main Menu");
        System.out.print("Enter: ");

        Scanner scan = new Scanner(System.in);
        String input = scan.nextLine();
        switch(input){
            case "1":
                changePassword(user);
                break;
            case "2":
                changeEmail(user);
                break;
            case "3":
                addStoreCredits(user);
                break;
            case "4":
                displayMainMenu();
                break;
            default:
                System.out.println("Wrong input.");
                break;
        }

    }

    private User addStoreCredits(User us){
        Scanner scan = new Scanner(System.in);
        System.out.println("How much store credits do you want to add?");
        System.out.println("Current credits to dollar ratio: 1000:$1");
        System.out.print("\nEnter: ");
        String input = scan.nextLine();
        System.out.println("Deducting payment from card...");

        us.setStorecredits(Integer.parseInt(input)); // Setting storeCredits
        userService.update(us);
        System.out.println("Purchase successful! Returning to account information.\n");
        displayAccountInfo();
        return us;
    }

    private User changeEmail(User us){
        Scanner scan = new Scanner(System.in);
        System.out.println("Current email: " + us.getEmail());
        while(true) {
            System.out.print("\nEnter new email: ");
            String newEmail = scan.nextLine();
            System.out.print("Confirm email: ");
            String confirmEmail = scan.nextLine();
            if(confirmEmail.equals(newEmail)){
                System.out.println("Updating email...");
                us.setEmail(confirmEmail);
                userService.update(us);
                System.out.println("Email updated successfully!");
                displayAccountInfo();
                break;
            } else System.out.println("Emails entered does not match!");
        }
        return us;

    }

    private User changePassword(User us){
        Scanner scan = new Scanner(System.in);

        while(true) {
            System.out.print("Enter your current password: ");
            String input = scan.nextLine();
                if(input.equals(us.getPassword())){
                    System.out.print("Enter new password: ");
                    String newPass = scan.nextLine();
                    userService.isValidPassWord(newPass);
                    System.out.println("Changing password...");
                    us.setPassword(newPass);
                    userService.update(us);
                    System.out.println("Password changed successfully!");
                    displayAccountInfo();
                    break;
//                    new StartMenu(userService).start();
            } else {
                    System.out.println("Invalid Password.");
                }
        }
        return us;
    }



}
