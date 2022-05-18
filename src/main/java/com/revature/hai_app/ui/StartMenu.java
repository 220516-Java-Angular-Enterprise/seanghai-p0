package com.revature.hai_app.ui;


// Should have options for user to direct application to. e.g Log In/ Create Account / Exit
import com.revature.hai_app.services.UserService;
import java.util.*;

public class StartMenu implements MenuTemplate {
    static Scanner scanner = new Scanner(System.in);
    String username;
    String password;
    //    Dependency injection
    private final UserService userService;

    //    Constructor
    public StartMenu(UserService userService) {
        this.userService = userService;
    }


    //    Start here
    @Override
    public void start() {
        displayIntroMenu();
        String input = scanner.nextLine();

//    Select menu
        switch (input) {
            case "1":
                login();
                break;
            case "2":
                createAcc();
                break;
            case "3":
                System.out.println("Bye!");
                break;
            default:
                System.out.println("Wrong input.");
                break;
        }

    }

    private void displayIntroMenu() {
        System.out.println("Welcome to my shopping app! " + "Press any key to continue.");
        scanner.nextLine();
        System.out.println("Please select from the following options.");
        System.out.println("[1] Login");
        System.out.println("[2] Create Account");
        System.out.println("[3] Exit");
    }

    private void login() {
        System.out.println("To be put in...");
    }

    private void createAcc() {
        System.out.println("Let's start creating your new account!");
        username = createUserName();
        password = createPassword();

        while (true) { //Confirm password
            System.out.print("Confirm password: ");
            String confirmPassword = scanner.nextLine();

            if (confirmPassword.equals(password)) {
                System.out.println("Account successfully created!");
                break;
            } else {
                System.out.println("Passwords do not match. Try again.");
                createPassword();
            }
        }

        System.out.print("Press enter to log in");
        scanner.next();
        login();

    }

    private String createUserName() {
        while (true) { // Make username
            System.out.print("Username: ");
            username = scanner.nextLine();

            if (userService.isValidUsername(username)) {

                break;
            } else System.out.println("Invalid username. Try another one.");
        }
        return username;
    }

    private String createPassword() {
        while (true) { // Make password
            System.out.print("Password: ");
            password = scanner.nextLine();

            if (userService.isValidPassWord(password)) {
                break;
            } else System.out.println("Minimum eight characters, at least one letter, one number and one special character");
        }
        return password;
    }
}


