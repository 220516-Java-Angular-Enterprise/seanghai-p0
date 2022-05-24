package com.revature.hai_app.models;


public class User {
    private String username;
    private String password;
    private String role;
    private String id;
    private String email;
    private String address;
    private String state;
    private int storecredits;


//    Constructor
    public User() {
    }

    public User(String username, String password, String role, String id, String email, String address, String state, int storecredits) {
        this.username = username;
        this.password = password;
        this.role = role;
        this.id = id;
        this.email = email;
        this.address = address;
        this.state = state;
        this.storecredits = storecredits;
    }

// Setters & Getters


    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getRole() {
        return role;
    }

    public String getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getAddress() {
        return address;
    }

    public String getState() {
        return state;
    }

    public int getStorecredits() {
        return storecredits;
    }

    public void setStorecredits(int storecredits) {
        this.storecredits = storecredits;
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

    public void setId(String id) {
        this.id = id;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setState(String state) {
        this.state = state;
    }



    @Override
    public String toString() {
        return "User{" +
                ", id='" + id + '\'' +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", role='" + role + '\'' +
                ", email='" + email + '\'' +
                ", address='" + address + '\'' +
                ", state='" + state + '\'' +
                '}';
    }
}
