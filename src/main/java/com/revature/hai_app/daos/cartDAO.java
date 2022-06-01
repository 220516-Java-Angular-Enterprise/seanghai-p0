package com.revature.hai_app.daos;

import com.revature.hai_app.models.Cart;
import com.revature.hai_app.util.database.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class cartDAO implements CrudeDAO<Cart>{
    Connection con = DatabaseConnection.getCon();
    @Override
    public void save(Cart obj) {
        try {
            PreparedStatement ps = con.prepareStatement("INSERT INTO carts (order_id, product_id, cart_prodprice_total, cart_prod_count, checked_out, cart_id) VALUES (?, ?, ?, ?, ?, ?)");

            ps.setString(1, obj.getOrder_id());
            ps.setString(2, obj.getProduct_id());
            ps.setInt(3, obj.getCart_prodprice_total());
            ps.setInt(4, obj.getCart_count());
            ps.setBoolean(5, obj.isChecked_out());
            ps.setString(6, obj.getCart_id());
            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println("SQLException: " + e.getMessage());
            System.out.println("SQLState: " + e.getSQLState());
            System.out.println("VendorError: " + e.getErrorCode());
        }
    }

    @Override
    public void update(Cart Obj) {
        try{
            PreparedStatement ps = con.prepareStatement("UPDATE carts SET " +
                    "cart_prod_count = '" + Obj.getCart_count() + "', " +
                    "cart_prodprice_total = '" + Obj.getCart_prodprice_total() + "', " +
                    "checked_out = '" + Obj.isChecked_out() + "' " +
                    "WHERE cart_id = '" + Obj.getCart_id() + "';");
            ps.executeUpdate();
        }   catch (SQLException e){
            System.out.println("SQLException: " + e.getMessage());
            System.out.println("SQLState: " + e.getSQLState());
            System.out.println("VendorError: " + e.getErrorCode());
        }
    }

    @Override
    public void delete(String id) {
        try{
            PreparedStatement ps = con.prepareStatement("DELETE FROM carts where cart_id ='"+id+"' AND checked_out ='"+false+"';");
            ps.executeUpdate();
        } catch (SQLException e){
            System.out.println("SQLException: " + e.getMessage());
            System.out.println("SQLState: " + e.getSQLState());
            System.out.println("VendorError: " + e.getErrorCode());
        }
    }

    @Override
    public Cart getByID(String id) {
        Cart car = new Cart();
        try{
            PreparedStatement ps = con.prepareStatement("SELECT * FROM carts WHERE cart_id='"+id+"';");
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                Cart ca = new Cart(
                        rs.getString("order_id"),
                        rs.getString("product_id"),
                        rs.getString("cart_id"),
                        rs.getInt("cart_prod_count"),
                        rs.getInt("cart_prodprice_total"),
                        rs.getBoolean("checked_out")
                );
                car = ca;
            }
        } catch (SQLException e){
            System.out.println("SQLException: " + e.getMessage());
            System.out.println("SQLState: " + e.getSQLState());
            System.out.println("VendorError: " + e.getErrorCode());
        }
        return car;
    }

    public List<Cart> getCartsByID(String id){
        List<Cart> carts = new ArrayList<>();
        try{
            PreparedStatement ps = con.prepareStatement("SELECT * FROM carts WHERE cart_id='"+id+"';");
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                Cart ca = new Cart(
                        rs.getString("order_id"),
                        rs.getString("product_id"),
                        rs.getString("cart_id"),
                        rs.getInt("cart_prod_count"),
                        rs.getInt("cart_prodprice_total"),
                        rs.getBoolean("checked_out")
                );
                carts.add(ca);
            }
        } catch (SQLException e){
            System.out.println("SQLException: " + e.getMessage());
            System.out.println("SQLState: " + e.getSQLState());
            System.out.println("VendorError: " + e.getErrorCode());
        }
        return carts;
    }

    public void removeItemByCartProdID(String cartID, String prodID){
        try{
            PreparedStatement ps = con.prepareStatement("DELETE FROM carts WHERE cart_id ='"+cartID+"' AND product_id='"+prodID+"'");
            ps.executeUpdate();
        } catch(SQLException e){
            System.out.println("SQLException: " + e.getMessage());
            System.out.println("SQLState: " + e.getSQLState());
            System.out.println("VendorError: " + e.getErrorCode());
        }
    }

    public List<String> getProdIDsByOrderID(String id){
        List<Cart> carts = new ArrayList<>();
        List<String> prodIDS = new ArrayList<>();
        try{
            PreparedStatement ps = con.prepareStatement("SELECT * FROM carts WHERE order_id='"+id+"' AND checked_out = TRUE;");
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                Cart ca = new Cart(
                        rs.getString("order_id"),
                        rs.getString("product_id"),
                        rs.getString("cart_id"),
                        rs.getInt("cart_prod_count"),
                        rs.getInt("cart_prodprice_total"),
                        rs.getBoolean("checked_out")
                );
                carts.add(ca);
            }
        } catch (SQLException e){
            System.out.println("SQLException: " + e.getMessage());
            System.out.println("SQLState: " + e.getSQLState());
            System.out.println("VendorError: " + e.getErrorCode());
        }
        for(Cart ca:carts){
            if (!ca.getProduct_id().equals("NULL")) prodIDS.add(ca.getProduct_id());
        }
        return prodIDS;
    }
    @Override
    public List<Cart> getAll() {
        return null;
    }
}
