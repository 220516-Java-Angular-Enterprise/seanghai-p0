package com.revature.hai_app.services;

import com.revature.hai_app.daos.userDAO;
import com.revature.hai_app.models.User;
import com.revature.hai_app.util.custom_exceptions.InvalidEmailException;
import com.revature.hai_app.util.custom_exceptions.InvalidPasswordException;
import com.revature.hai_app.util.custom_exceptions.InvalidUserException;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UserService {
    //Injecting
    private final userDAO userDAO;
    public UserService(userDAO userDAO) { this.userDAO = userDAO;};
    public User login(String username, String password){
        User user = userDAO.getUserbyUsernameAndPassword(username, password);

        if (isValidCredentials(user)) return user;
        return null;
    }

    public void register(User user){
        userDAO.save(user);
    }
    public boolean isValidUsername(String username){
        if (username.matches("^(?=[a-zA-Z0-9._]{8,20}$)(?!.*[_.]{2})[^_.].*[^_.]$")){
            return true;
        } else {
            throw new InvalidUserException("Invalid username. Try another one.");
        }
    }

    public boolean isValidPassWord(String password){
        if (password.matches("^(?=.*[A-Za-z])(?=.*\\d)(?=.*[@$!%*#?&])[A-Za-z\\d@$!%*#?&]{8,}$")) {
            return true;
        } else {
            throw new InvalidPasswordException("Minimum eight characters, at least one letter, one number and one special character");
        }
    }

    public boolean isValidCredentials(User user){
        if (user.getUsername() == null && user.getPassword() == null){
            throw new InvalidUserException("Incorrect username and password.");
        } else if (user.getUsername() == null) {
            throw new InvalidUserException("The username you entered does not exist.");
        } else if (user.getPassword() == null){
            throw new InvalidPasswordException("The password you entered does not match the username.");
        }
        return true;
    }

    public boolean isValidEmail(String email){
        if (email.matches("^[a-zA-Z0-9_!#$%&’*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$")) {
            return true;
        } else {
            throw new InvalidEmailException("Invalid email entered.");
        }
    }

    }

