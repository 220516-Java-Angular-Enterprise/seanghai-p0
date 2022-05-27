package com.revature.hai_app.daos;

import com.revature.hai_app.models.Store;
import com.revature.hai_app.models.User;
import com.revature.hai_app.util.database.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class storeDAO implements CrudeDAO<Store>{
    Connection con = DatabaseConnection.getCon();
    @Override
    public void save(Store obj) {
    try {
        PreparedStatement ps = con.prepareStatement("INSERT into stores " +
                "(id, location) VALUES " +
                "(?, ?)");
            ps.setString(1, obj.getId());
            ps.setString(2, obj.getLocation());
            ps.executeUpdate();
    } catch (SQLException e){
        System.out.println("SQLException: " + e.getMessage());
        System.out.println("SQLState: " + e.getSQLState());
        System.out.println("VendorError: " + e.getErrorCode());
    }
    }

    @Override
    public void update(Store Obj) {
    try {
        PreparedStatement ps = con.prepareStatement("UPDATE users SET " +
                "location = '" + Obj.getLocation() + "'," +
                "WHERE id = '" + Obj.getId() + "';");
                ps.executeUpdate();
    } catch (SQLException e){
        System.out.println("SQLException: " + e.getMessage());
        System.out.println("SQLState: " + e.getSQLState());
        System.out.println("VendorError: " + e.getErrorCode());
    }
    }

    @Override
    public void delete(String id) {

    }

    @Override
    public Store getByID(String id) {
        Store stor = new Store();
        try {
            PreparedStatement ps = con.prepareStatement("SELECT * FROM stores WHERE id='"+id+"';");
            ResultSet rs = ps.executeQuery();
            while(rs.next()) {
                Store sto = new Store(
                        rs.getString("id"),
                        rs.getString("location")
                );
                stor = sto;
            }
        } catch (SQLException e) {
            System.out.println("SQLException: " + e.getMessage());
            System.out.println("SQLState: " + e.getSQLState());
            System.out.println("VendorError: " + e.getErrorCode());
        }
        return stor;
    }

    @Override
    public List<Store> getAll() {
        List<Store> stores = new ArrayList<>();

        try {
            PreparedStatement ps = con.prepareStatement("SELECT * FROM stores;");
            ResultSet rs = ps.executeQuery();

            while(rs.next()){
                Store store = new Store(
                        rs.getString("id"),
                        rs.getString("location")
                );
                stores.add(store);
            }
        } catch (SQLException e) {
            System.out.println("SQLException: " + e.getMessage());
            System.out.println("SQLState: " + e.getSQLState());
            System.out.println("VendorError: " + e.getErrorCode());
        }
        return stores;
    }
}
