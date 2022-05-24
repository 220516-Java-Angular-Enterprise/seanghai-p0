package com.revature.hai_app.services;

import com.revature.hai_app.daos.userDAO;
import com.revature.hai_app.models.User;
import com.revature.hai_app.util.custom_exceptions.InvalidAddressException;
import com.revature.hai_app.util.custom_exceptions.InvalidEmailException;
import com.revature.hai_app.util.custom_exceptions.InvalidPasswordException;
import com.revature.hai_app.util.custom_exceptions.InvalidUserException;
import sun.plugin.dom.exception.InvalidStateException;

import java.util.List;

public class UserService {
    //Injecting
    private final userDAO userDAO;
    public UserService(userDAO userDAO) { this.userDAO = userDAO;};
    public User login(String username, String password){
        User user = new User();
        List<User> users = userDAO.getAll();

        for (User u: users){
            if (u.getUsername().equals(username)){
                user.setId(u.getId());
                user.setUsername(u.getUsername());
                user.setRole(u.getRole());
                if (u.getPassword().equals(password)){
                    user.setPassword((u.getPassword()));
                    user.setEmail(u.getEmail());
                    user.setAddress(u.getAddress());
                    user.setState(u.getState());
                    user.setStorecredits(u.getStorecredits());
                    break;
                }
            }
            if (u.getPassword().equals(password)){
                user.setPassword(u.getPassword());
                break;
            }
        }
        return isValidCredentials(user);
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

    public User isValidCredentials(User user){
        if (user.getUsername() == null && user.getPassword() == null){
            throw new InvalidUserException("Incorrect username and password.");
        } else if (user.getUsername() == null) {
            throw new InvalidUserException("The username you entered does not exist.");
        } else if (user.getPassword() == null){
            throw new InvalidPasswordException("The password you entered does not match the username.");
        }
        return user;
    }

    public boolean isValidEmail(String email){
        if (email.matches("^[a-zA-Z0-9_!#$%&’*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$")) {
            return true;
        } else {
            throw new InvalidEmailException("Invalid email entered.");
        }
    }

    public boolean isValidAddress(String address){
        if (address.matches("\\d{1,5}\\s\\w.\\s(\\b\\w*\\b\\s){1,2}\\w*\\.")) {
            return true;
        } else {
            throw new InvalidAddressException("Invalid address entered.");
        }
    }

    public boolean isValidState(String state){
        if (state.matches("^((A[ELKSZR])|(C[AOT])|(D[EC])|(F[ML])|(G[AU])|(HI)|(I[DLNA])|(K[SY])|(LA)|(M[EHDAINSOT])|(N[EVHJMYCD])|(MP)|(O[HKR])|(P[WAR])|(RI)|(S[CD])|(T[NX])|(UT)|(V[TIA])|(W[AVIY]))$")) {
            return true;
        } else {
            throw new InvalidStateException("Invalid state entered.");
        }
    }


    }

