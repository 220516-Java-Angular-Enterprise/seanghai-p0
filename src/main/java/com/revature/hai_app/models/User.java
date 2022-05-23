package com.revature.hai_app.models;

import java.util.*;


public class User {
    private String username;
    private String password;
    private String role;
    private String id;
    private String email;

//    Constructor
    public User() {
    }

    public User(String id, String username, String password, String role, String email) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.email = email;
        this.role = role;
    }

 // Setters & Getters

    public String getUsername() {
        return username;
    }
    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

    public String getRole() {
        return role;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String toFileString(){
        return id + "/" + username + "/" + password + "/" + email + "/" + role + "\n";
    }

    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", role='" + role + '\'' +
                ", id='" + id + '\'' +
                ", email='" + email + '\'' +
                '}';
    }


}
