package com.revature.hai_app.ui;


// Should have options for user to direct application to. e.g Log In/ Create Account / Exit
import com.revature.hai_app.models.User;
import com.revature.hai_app.services.UserService;
import com.revature.hai_app.util.custom_exceptions.InvalidEmailException;
import com.revature.hai_app.util.custom_exceptions.InvalidPasswordException;
import com.revature.hai_app.util.custom_exceptions.InvalidUserException;

import java.util.*;

public class StartMenu implements MenuTemplate {
    // Global Attributes
    static Scanner scanner = new Scanner(System.in);
    String username;
    String password;
    String email;
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
        User user = new User();

        while (true) {
            System.out.println("Logging in account...");
            System.out.print("Username: ");
            username = scanner.nextLine();

            System.out.print("Password: ");
            password = scanner.nextLine();

            try {
                user = userService.login(username, password);

                if (user.getRole().equals("ADIM")) new AdminMenu().start();
                else new MainMenu(user).start();
                break;
            } catch (InvalidUserException e) {
                System.out.println(e.getMessage());
            }
        }

    }

    private void createAcc() {
        System.out.println("Let's start creating your new account!");

        completeExit:
        {
            while (true) {
                username = createUserName();
                password = createPassword();
                email = createEmail();
                System.out.print("Confirm password: "); // Confirm Password
                String confirmPassword = scanner.nextLine();

                if (confirmPassword.equals(password)) {
                    System.out.println("Let's confirm your information!");

                } else {
                    System.out.println("Passwords do not match. Try again.");
                    createPassword();
                }

                confirmExit:
                {
                    System.out.println("Please confirm your entered information (y/n)");
                    System.out.println("Username: " + username);
                    System.out.println("Password: " + password);
                    System.out.println("Email: " + email);

                    System.out.print("Input: ");
                    String input = scanner.nextLine();

                    switch (input) {
                        case "y":
                            User user = new User(UUID.randomUUID().toString(), username, password, "DEFAULT", email);

                            userService.register(user);
                            login();
                            break;
                        case "n":
                            break confirmExit;
                        default:
                            System.out.println("Invalid input");
                            break;

                    }

                }
            }
        }
    }

    private String createUserName() {
        while (true) { // Make username
            System.out.print("Username: ");
            username = scanner.nextLine();

            try {
                if (userService.isValidUsername(username)) {
                    break;

                }
            }  catch(InvalidUserException e){
                    System.out.println(e.getMessage());
                }
            }
        return username;
        }


    private String createPassword() {
        while (true) { // Make password
            System.out.print("Password: ");
            password = scanner.nextLine();

            try {
                if (userService.isValidPassWord(password)) break;
                }
            catch (InvalidPasswordException e){
                    System.out.println(e.getMessage());
            }
        }
        return password;
    }
    private String createEmail(){
        while(true){
            System.out.print("Email: ");
            email = scanner.nextLine();

            try{
                if (userService.isValidEmail(email)) break;
            } catch (InvalidEmailException e){
                System.out.println(e.getMessage());
            }
        }
        return email;
    }
    }





