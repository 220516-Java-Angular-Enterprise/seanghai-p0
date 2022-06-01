package com.revature.hai_app.daos;

import com.revature.hai_app.models.User;
import com.revature.hai_app.util.database.DatabaseConnection;

import java.io.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class userDAO implements CrudeDAO<User> {
    Connection con = DatabaseConnection.getCon();

    @Override
    public void save(User obj) {
        try {
            PreparedStatement ps = con.prepareStatement("INSERT INTO users (id, username, password, storecredits, address, state, role, email) VALUES (?, ?, ?, ?, ?, ?, ?, ?)");

            ps.setString(1, obj.getId());
            ps.setString(2, obj.getUsername());
            ps.setString(3, obj.getPassword());
            ps.setInt(4, obj.getStorecredits());
            ps.setString(5, obj.getAddress());
            ps.setString(6, obj.getState());
            ps.setString(7, obj.getRole());
            ps.setString(8, obj.getEmail());
            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println("SQLException: " + e.getMessage());
            System.out.println("SQLState: " + e.getSQLState());
            System.out.println("VendorError: " + e.getErrorCode());
        }

    }

    @Override
    public void update(User Obj) {
    try{
        PreparedStatement ps = con.prepareStatement("UPDATE users SET " +
                "password = '" + Obj.getPassword() + "'," +
                "storecredits = '" + Obj.getStorecredits() + "'," +
                "address = '" + Obj.getAddress() + "'," +
                "state = '" + Obj.getState() + "'," +
                "email = '" + Obj.getEmail() + "' " +
                "WHERE id = '" + Obj.getId() + "';");
                ps.executeUpdate();
    }   catch (SQLException e){
        System.out.println("SQLException: " + e.getMessage());
        System.out.println("SQLState: " + e.getSQLState());
        System.out.println("VendorError: " + e.getErrorCode());
    }
    }

    @Override
    public void delete(String id) {
        try {
            PreparedStatement ps = con.prepareStatement("DELETE FROM users WHERE id ='" + id + "';");
            ps.executeUpdate();
        } catch (SQLException e){
            System.out.println("SQLException: " + e.getMessage());
            System.out.println("SQLState: " + e.getSQLState());
            System.out.println("VendorError: " + e.getErrorCode());
        }
    }

    @Override
    public User getByID(String id) {
        User us = new User();
        try{
            PreparedStatement ps = con.prepareStatement("SELECT * FROM users WHERE id = '" + id + "';");
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                User user = new User(
                        rs.getString("username"),
                        rs.getString("password"),
                        rs.getString("role"),
                        rs.getString("id"),
                        rs.getString("email"),
                        rs.getString("address"),
                        rs.getString("state"),
                        rs.getInt("storecredits")
                );
                us = user;
            }
        } catch (SQLException e){
            System.out.println("SQLException: " + e.getMessage());
            System.out.println("SQLState: " + e.getSQLState());
            System.out.println("VendorError: " + e.getErrorCode());
        }
        return us;
    }

    @Override
    public List<User> getAll() {
        List<User> users = new ArrayList<>();

        try {
            PreparedStatement ps = con.prepareStatement("SELECT * FROM users");
            ResultSet rs = ps.executeQuery();

            while(rs.next()){
                User user = new User(
                        rs.getString("username"),
                        rs.getString("password"),
                        rs.getString("role"),
                        rs.getString("id"),
                        rs.getString("email"),
                        rs.getString("address"),
                        rs.getString("state"),
                        rs.getInt("storecredits")
                );
                users.add(user);
            }
        } catch (SQLException e) {
            System.out.println("SQLException: " + e.getMessage());
            System.out.println("SQLState: " + e.getSQLState());
            System.out.println("VendorError: " + e.getErrorCode());
        }
        return users;
    }

    public List<User> searchUsersByUsername(String search){
        List<User> users = new ArrayList<>();
        try{
            PreparedStatement ps = con.prepareStatement("SELECT * FROM users WHERE username LIKE '%"+search+"%';");
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                User user = new User(
                        rs.getString("username"),
                        rs.getString("password"),
                        rs.getString("role"),
                        rs.getString("id"),
                        rs.getString("email"),
                        rs.getString("address"),
                        rs.getString("state"),
                        rs.getInt("storecredits")
                );
                users.add(user);
            }
        } catch (SQLException e){
            System.out.println("SQLException: " + e.getMessage());
            System.out.println("SQLState: " + e.getSQLState());
            System.out.println("VendorError: " + e.getErrorCode());
        }
        return users;
    }
    public List<String> getAllUsernames() {
        List<String> usernames = new ArrayList();

        try {
            PreparedStatement ps = con.prepareStatement("Select username FROM users");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {

                usernames.add(rs.getString("username"));
            }
        } catch (SQLException e) {
            throw new RuntimeException("n error occurred when tyring to get data from to the database.");
        }
        return usernames;
    }

    public List<String>  getAllEmails(){
        List<String> emails = new ArrayList<>();
        try {
            PreparedStatement ps = con.prepareStatement("Select email FROM users");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                emails.add(rs.getString("email"));
            }
        } catch (SQLException e) {
            throw new RuntimeException("n error occurred when tyring to get data from to the database.");
        }
        return emails;
    }
}
