package com.revature.hai_app.daos;

import com.revature.hai_app.models.Inventinstance;
import com.revature.hai_app.models.Orderinstance;
import com.revature.hai_app.util.database.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class orderInstDAO implements CrudeDAO<Orderinstance>{
    Connection con = DatabaseConnection.getCon();
    @Override
    public void save(Orderinstance obj) {
        try {
            PreparedStatement ps = con.prepareStatement("INSERT INTO orderinstance (store_id, order_id) VALUES (?, ?);");
            ps.setString(1, obj.getStore_id());
            ps.setString(2, obj.getOrder_id());
            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println("SQLException: " + e.getMessage());
            System.out.println("SQLState: " + e.getSQLState());
            System.out.println("VendorError: " + e.getErrorCode());
        }
    }

    @Override
    public void update(Orderinstance Obj) {

    }

    @Override
    public void delete(String id) {

    }

    public Orderinstance getByStoreID(String id){
        Orderinstance invent = new Orderinstance();
        try{
            PreparedStatement ps = con.prepareStatement("SELECT * from inventinstances WHERE store_id='"+id+"';");
            ResultSet rs =  ps.executeQuery();
            while(rs.next()){
                Orderinstance inv = new Orderinstance(
                        rs.getString("store_id"),
                        rs.getString("product_id")
                );
                invent = inv;
            }
        } catch(SQLException e){
            System.out.println("SQLException: " + e.getMessage());
            System.out.println("SQLState: " + e.getSQLState());
            System.out.println("VendorError: " + e.getErrorCode());
        }
        return invent;
    }

    public Orderinstance getByOrderID(String id){
        Orderinstance invent = new Orderinstance();
        try{
            PreparedStatement ps = con.prepareStatement("SELECT * from orderinstance WHERE order_id= '"+id+"';");
            ResultSet rs =  ps.executeQuery();
            while(rs.next()){
                Orderinstance inv = new Orderinstance(
                        rs.getString("store_id"),
                        rs.getString("order_id")
                );
                invent = inv;
            }
        } catch(SQLException e){
            System.out.println("SQLException: " + e.getMessage());
            System.out.println("SQLState: " + e.getSQLState());
            System.out.println("VendorError: " + e.getErrorCode());
        }
        return invent;
    }

    public List<Orderinstance> getAllbyStoreID(String id){
        List<Orderinstance> ordersByStore = new ArrayList<>();
        try{
            PreparedStatement ps = con.prepareStatement("SELECT * from orderinstance WHERE store_id='"+id+"';");
            ResultSet rs =  ps.executeQuery();
            while(rs.next()){
                Orderinstance inv = new Orderinstance(
                        rs.getString("store_id"),
                        rs.getString("order_id")
                );
                ordersByStore.add(inv);
            }
        } catch(SQLException e){
            System.out.println("SQLException: " + e.getMessage());
            System.out.println("SQLState: " + e.getSQLState());
            System.out.println("VendorError: " + e.getErrorCode());
        }
        return ordersByStore;
    }
    @Override
    public Orderinstance getByID(String id) {
        return null;
    }

    @Override
    public List<Orderinstance> getAll() {
        return null;
    }
}
