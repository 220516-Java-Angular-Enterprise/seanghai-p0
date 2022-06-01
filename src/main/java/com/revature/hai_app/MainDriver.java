package com.revature.hai_app;


import com.revature.hai_app.daos.userDAO;
import com.revature.hai_app.services.FontService;
import com.revature.hai_app.services.UserService;
import com.revature.hai_app.ui.StartMenu;
import com.revature.hai_app.util.database.DatabaseConnection;

// Main purpose is to start application
public class MainDriver {
    public static void main(String[] args){
        userDAO userDAO = new userDAO();
        UserService userService = new UserService(userDAO);
        StartMenu start = new StartMenu(userService);
        FontService font = new FontService();

        start.start();
//        System.out.println(DatabaseConnection.getCon());

    }
}
