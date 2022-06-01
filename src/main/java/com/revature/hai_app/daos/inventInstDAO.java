package com.revature.hai_app.daos;

import com.revature.hai_app.models.Inventinstance;
import com.revature.hai_app.util.database.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class inventInstDAO implements CrudeDAO<Inventinstance>{
    Connection con = DatabaseConnection.getCon();
    @Override
    public void save(Inventinstance obj) {
        try {
            PreparedStatement ps = con.prepareStatement("INSERT INTO inventinstances (store_id, product_id, prod_count) VALUES (?, ?, ?);");

            ps.setString(1, obj.getStore_id());
            ps.setString(2, obj.getProduct_id());
            ps.setInt(3, obj.getProd_count());
            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println("SQLException: " + e.getMessage());
            System.out.println("SQLState: " + e.getSQLState());
            System.out.println("VendorError: " + e.getErrorCode());
        }
    }

    @Override
    public void update(Inventinstance Obj) {
        try{
            PreparedStatement ps = con.prepareStatement("UPDATE inventinstances SET " +
                    "product_id = '"+ Obj.getProduct_id()+"', " +
                    "prod_count = '"+ Obj.getProd_count()+"' " +
                    "WHERE store_id='"+Obj.getStore_id()+"';");
                    ps.executeUpdate();
        } catch(SQLException e){
            System.out.println("SQLException: " + e.getMessage());
            System.out.println("SQLState: " + e.getSQLState());
            System.out.println("VendorError: " + e.getErrorCode());
        }
    }

    public void updateProdCount(Inventinstance Obj){try{
        PreparedStatement ps = con.prepareStatement("UPDATE inventinstances SET " +
                "prod_count = '"+ Obj.getProd_count()+"' " +
                "WHERE store_id='"+Obj.getStore_id()+"' " +
                "AND product_id='"+Obj.getProduct_id()+"';");
                ps.executeUpdate();
    } catch(SQLException e){
        System.out.println("SQLException: " + e.getMessage());
        System.out.println("SQLState: " + e.getSQLState());
        System.out.println("VendorError: " + e.getErrorCode());
    }
}




    public Inventinstance getByStoreID(String id){
        Inventinstance invent = new Inventinstance();
        try{
            PreparedStatement ps = con.prepareStatement("SELECT * from inventinstances WHERE store_id='"+id+"';");
            ResultSet rs =  ps.executeQuery();
            while(rs.next()){
                Inventinstance inv = new Inventinstance(
                        rs.getString("store_id"),
                        rs.getString("product_id"),
                        rs.getInt("prod_count")
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

    public Inventinstance getByProdID(String id){
        Inventinstance invent = new Inventinstance();
        try{
            PreparedStatement ps = con.prepareStatement("SELECT * from inventinstances WHERE product_id='"+id+"';");
            ResultSet rs =  ps.executeQuery();
            while(rs.next()){
                Inventinstance inv = new Inventinstance(
                        rs.getString("store_id"),
                        rs.getString("product_id"),
                        rs.getInt("prod_count")
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

    public List<Inventinstance> getAllByStoreID(String id){
        List<Inventinstance> inventoriesByStore = new ArrayList<>();
        try{
            PreparedStatement ps = con.prepareStatement("SELECT * from inventinstances WHERE store_id='"+id+"';");
            ResultSet rs =  ps.executeQuery();
            while(rs.next()){
                Inventinstance inv = new Inventinstance(
                        rs.getString("store_id"),
                        rs.getString("product_id"),
                        rs.getInt("prod_count")
                );
                inventoriesByStore.add(inv);
            }
        } catch(SQLException e){
            System.out.println("SQLException: " + e.getMessage());
            System.out.println("SQLState: " + e.getSQLState());
            System.out.println("VendorError: " + e.getErrorCode());
        }
        return inventoriesByStore;
    }
    @Override
    public void delete(String id) {

    }

    @Override
    public Inventinstance getByID(String id) {
        return null;
    }

    @Override
    public List<Inventinstance> getAll() {
        return null;
    }
}
