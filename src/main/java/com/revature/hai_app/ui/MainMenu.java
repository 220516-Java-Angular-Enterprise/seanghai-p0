package com.revature.hai_app.ui;

import com.revature.hai_app.models.User;
import com.revature.hai_app.services.UserService;

import java.awt.*;
import java.util.*;

public class MainMenu implements MenuTemplate{
    // Dependency
    private final User user;
    public MainMenu(User user){this.user=user;}
    UserService userService;
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
        System.out.println("Password: " + user.getPassword());
        System.out.println("Email: " + user.getEmail());
        System.out.println("[1] Change password");
        System.out.println("[2] Change email");
        System.out.println("[3] Back to Main Menu");

        Scanner scan = new Scanner(System.in);
        String input = scan.nextLine();
        switch(input){
            case "1":
                System.out.println("Changing password...");
                break;
            case "2":
                System.out.println("Changing email...");
                break;
            case "3":
                displayMainMenu();
                break;
            default:
                System.out.println("Wrong input.");
                break;
        }

    }

    private void changePassword(){

    }


}
