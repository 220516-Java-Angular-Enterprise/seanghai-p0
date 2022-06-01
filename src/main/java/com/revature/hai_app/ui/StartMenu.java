package com.revature.hai_app.ui;


// Should have options for user to direct application to. e.g Log In/ Create Account / Exit
import com.revature.hai_app.daos.cartDAO;
import com.revature.hai_app.models.Cart;
import com.revature.hai_app.models.User;
import com.revature.hai_app.services.CartService;
import com.revature.hai_app.services.FontService;
import com.revature.hai_app.services.UserService;
import com.revature.hai_app.util.custom_exceptions.*;

import java.util.*;

public class StartMenu implements MenuTemplate {
    // Global Attributes
    static Scanner scanner = new Scanner(System.in);

    //    Dependency injection
    private final UserService userService;
    //    Constructor
    public StartMenu(UserService userService) {
        this.userService = userService;
    }

    // Font Service
    FontService font = new FontService();
    //    Start here
    @Override
    public void start() {
        displayIntroMenu();
        System.out.print(font.green("\nEnter: "));
        String input = scanner.nextLine();
        exit:{
//    Select menu
        while(true) {
            switch (input) {
                case "1":
                    login();
                    break;
                case "2":
                    createAcc();
                    break;
                case "3":
                    System.out.println(font.purpleBold("\nWe await your return..."));
                    break exit;
                default:
                    System.out.println("Wrong input.");
            }
        }
        }
    }

    private void displayIntroMenu() {
        System.out.println(font.yellow("<+------------------------------------------------------------+>\n"));
        System.out.println(font.purpleBold("             WELCOME TO THE INTERDIMENSIONAL RIFT SHOP\n"));
        System.out.println(font.greenBold("                     Press Enter to Proceed\n"));
        System.out.println(font.yellow("<+------------------------------------------------------------+>\n"));
        scanner.nextLine();
        System.out.println(font.yellow("+------------------------------------------------------------>\n"));
        System.out.println(font.greenBold("            PLEASE SELECT FROM THE FOLLOWING OPTIONS.\n"));
        System.out.println(font.yellow("+------------------------------------------------------------>\n"));
        System.out.println("[1]"+font.whiteBold(" Login\n"));
        System.out.println("[2]"+font.whiteBold(" Create Account\n"));
        System.out.println("[3]"+font.redBold(" Exit"));
    }

    private void login() {
        String username;
        String password;
        User user = new User();
        while (true) {
            System.out.println(font.yellow("<+------------------------------------------------------------+>\n"));
            System.out.println(font.cyanBold("                             LOGIN\n"));
            System.out.println(font.yellow("<+------------------------------------------------------------+>\n"));
            System.out.print(font.whiteBold("                   Username: "));
            username = scanner.nextLine();

            System.out.print(font.whiteBold("\n                   Password: "));
            password = scanner.nextLine();
            System.out.println(font.yellow("\n<+------------------------------------------------------------+>\n"));
            try {
                user = userService.login(username, password);

                if(user.getRole().equals("ADMIN")) {
                    new AdminMenu().start();
                    break;
                }
                else {
                    new MainMenu(user).start();
                    break;
                    }
            } catch (InvalidUserException e) {
                System.out.println(e.getMessage());
            } catch (InvalidPasswordException e){
                System.out.println(e.getMessage());
            }
        }

    }

    private void createAcc() {
        System.out.println("Let's start creating your new account!");

        completeExit:
        {
            try {
                while (true) {

                    String username = createUserName();
                    String password = createPassword();
                    String address = createAddress();
                    String state = createState();
                    String email = createEmail();

                    System.out.print(font.whiteBold("Confirm password: ")); // Confirm Password
                    String confirmPassword = scanner.nextLine();

                    if (confirmPassword.equals(password)) {
                        System.out.println(font.greenBold("Let's confirm your information!"));

                    } else {
                        System.out.println(font.red("Passwords do not match. Try again."));
                        createPassword();
                    }

                    confirmExit:
                    {
                        System.out.println(font.greenBold("Please confirm your entered information (y/n)"));
                        System.out.println(font.whiteBold("Username: ") + username);
                        System.out.println(font.whiteBold("Password: ") + password);
                        System.out.println(font.whiteBold("Email: ") + email);

                        System.out.print(font.green("Input: "));
                        String input = scanner.nextLine();

                        switch (input) {
                            case "y":
                                User user = new User(username, password, "DEFAULT", UUID.randomUUID().toString(), email, address, state, 0);

                                userService.register(user);
                                login();
                                break;
                            case "n":
                                break confirmExit;
                            default:
                                System.out.println(font.red("Invalid input"));
                                break;

                        }

                    }
                }
            }
                 catch(InvalidAddressException e){
                    System.out.println(e.getMessage());
                } catch(InvalidStateException e){
                    System.out.println(e.getMessage());
                } catch(InvalidEmailException e){
                    System.out.println(e.getMessage());
                } catch(InvalidUserException e){
                    System.out.println(e.getMessage());
                } catch(InvalidPasswordException e){
                    System.out.println(e.getMessage());
                }
        }
    }
    private String createUserName() {
        String username;
        while (true) { // Make username
            System.out.print(font.whiteBold("Username: "));
            username = scanner.nextLine();

            try {
                if (userService.isValidUsername(username)) {
                    if(userService.isNotDuplicateUsername(username)) break;
                }
            }  catch(InvalidUserException e){
                    System.out.println(e.getMessage());
                }
            }
        return username;
        }
    private String createPassword() {
        String password;

        while (true) { // Make password
            System.out.print(font.whiteBold("Password: "));
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
        String email;

        while(true){
            System.out.print(font.whiteBold("Email: "));
            email = scanner.nextLine();

            try{
                if (userService.isValidEmail(email)){
                    if(userService.isNotDuplicateEmail(email)) break;
                }
            } catch (InvalidEmailException e){
                System.out.println(e.getMessage());
            }
        }
        return email;
    }
    private String createAddress(){
        String address;

        while(true){
            System.out.print(font.whiteBold("Address: "));
            address = scanner.nextLine();

            try{
                if (userService.isValidAddress(address)) break;
            } catch (InvalidAddressException e){
                System.out.println(e.getMessage());
            }
        }
        return address;
    }
    private String createState(){
        String state;
        while(true){
            System.out.print(font.whiteBold("State (e.g AZ): "));
            state = scanner.nextLine();

            try{
                if (userService.isValidState(state)) break;
            } catch (InvalidStateException e){
                System.out.println(e.getMessage());
            }
        }
        return state;
    }
    }





