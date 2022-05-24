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
            PreparedStatement ps = con.prepareStatement("INSERT INTO users (id, username, password, storecredits, address, state, role, email) VALUES (?, ?, ?, ?, ?, ?, ?, ?");
            ps.setString(1, obj.getId());
            ps.setString(2, obj.getUsername());
            ps.setString(3, obj.getPassword());
            ps.setString(4, String.valueOf(obj.getStorecredits()));
            ps.setString(5, obj.getAddress());
            ps.setString(6, obj.getState());
            ps.setString(7, obj.getRole());
            ps.setString(8, obj.getEmail());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("An error occurred when trying to save to database.");
        }

    }

    @Override
    public void update(User Obj) {

    }

    @Override
    public void delete(String id) {

    }

    @Override
    public User getByID(String id) {
        return null;
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
                        Integer.parseInt(rs.getString("storecredits"))
                );
                users.add(user);
            }
        } catch (SQLException e) {
            throw new RuntimeException("An error occurred when tyring to get data from to the database.");
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
}
