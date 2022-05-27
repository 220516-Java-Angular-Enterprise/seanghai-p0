package com.revature.hai_app.daos;

import com.revature.hai_app.models.Orders;
import com.revature.hai_app.models.User;
import com.revature.hai_app.util.database.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ordersDAO implements CrudeDAO<Orders>{
    Connection con = DatabaseConnection.getCon();
    @Override
    public void save(Orders obj) {
        try {
            PreparedStatement ps = con.prepareStatement("INSERT INTO orders (id, ord_date, price_total, product_qty, user_id) VALUES (?, ?, ?, ?, ?)");

            ps.setString(1, obj.getId());
            ps.setString(2, obj.getDate());
            ps.setInt(3, obj.getPrice_total());
            ps.setInt(4, obj.getProduct_qty());
            ps.setString(5, obj.getUser_id());
            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println("SQLException: " + e.getMessage());
            System.out.println("SQLState: " + e.getSQLState());
            System.out.println("VendorError: " + e.getErrorCode());
        }
    }

    @Override
    public void update(Orders Obj) {
        try{
            PreparedStatement ps = con.prepareStatement("UPDATE products SET " +
                    "ord_date = '" + Obj.getDate() + "'," +
                    "price_total = '" + Obj.getPrice_total() + "'," +
                    "product_qty = '" + Obj.getProduct_qty() + "'," +
                    "user_id = '" + Obj.getUser_id() + "' " +
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
            PreparedStatement ps = con.prepareStatement("DELETE FROM orders WHERE id ='" + id + "';");
            ps.executeUpdate();
        } catch (SQLException e){
            System.out.println("SQLException: " + e.getMessage());
            System.out.println("SQLState: " + e.getSQLState());
            System.out.println("VendorError: " + e.getErrorCode());
        }
    }

    @Override
    public Orders getByID(String id) {
        Orders ord = new Orders();
        try{
            PreparedStatement ps = con.prepareStatement("SELECT * FROM orders WHERE id='" + id + "';");
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                Orders order = new Orders(
                        rs.getString("id"),
                        rs.getString("date"),
                        rs.getInt("price_total"),
                        rs.getInt("product_qty"),
                        rs.getString("user_id")
                );
                order = ord;
            }
        } catch (SQLException e){
            System.out.println("SQLException: " + e.getMessage());
            System.out.println("SQLState: " + e.getSQLState());
            System.out.println("VendorError: " + e.getErrorCode());
        }
        return ord;
    }

    public List<Orders> getAllByUserId(String id){
        List<Orders> orders = new ArrayList<>();
        try{
            PreparedStatement ps = con.prepareStatement("SELECT * FROM orders WHERE user_id='"+id+"';");
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                Orders order = new Orders(
                        rs.getString("id"),
                        rs.getString("date"),
                        rs.getInt("price_total"),
                        rs.getInt("product_qty"),
                        rs.getString("user_id")
                );
                orders.add(order);
            }
        } catch (SQLException e ){
            System.out.println("SQLException: " + e.getMessage());
            System.out.println("SQLState: " + e.getSQLState());
            System.out.println("VendorError: " + e.getErrorCode());
        }
        return orders;
    }
    @Override
    public List<Orders> getAll() {
        List<Orders> orders = new ArrayList<>();

        try {
            PreparedStatement ps = con.prepareStatement("SELECT * FROM products;");
            ResultSet rs = ps.executeQuery();

            while(rs.next()){
                Orders order = new Orders(
                        rs.getString("id"),
                        rs.getString("date"),
                        rs.getInt("price_total"),
                        rs.getInt("product_qty"),
                        rs.getString("user_id")
                );
                orders.add(order);
            }
        } catch (SQLException e) {
            System.out.println("SQLException: " + e.getMessage());
            System.out.println("SQLState: " + e.getSQLState());
            System.out.println("VendorError: " + e.getErrorCode());
        }
        return orders;
    }
}
