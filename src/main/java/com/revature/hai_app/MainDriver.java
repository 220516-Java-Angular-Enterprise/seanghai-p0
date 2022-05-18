package com.revature.hai_app;


import com.revature.hai_app.services.UserService;
import com.revature.hai_app.ui.StartMenu;

// Main purpose is to start application
public class MainDriver {

    public static void main(String[] args){
        UserService userService = new UserService();
        StartMenu start = new StartMenu(userService);

        start.start();
    }
}
