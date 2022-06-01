package com.revature.hai_app.daos;

import com.revature.hai_app.models.Orderinstance;
import com.revature.hai_app.models.Product;
import com.revature.hai_app.util.database.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class productDAO implements CrudeDAO<Product>{
    Connection con = DatabaseConnection.getCon();
    @Override
    public void save(Product obj) {
        try{
            PreparedStatement ps = con.prepareStatement("INSERT into products " +
                    "(id, name, description, classrec, rarity, price) VALUES " +
                    "(?, ?, ?, ?, ?, ?)");
            ps.setString(1, obj.getId());
            ps.setString(2, obj.getName());
            ps.setString(3, obj.getDescription());
            ps.setString(4, obj.getClassRec());
            ps.setString(5, obj.getRarity());
            ps.setInt(6, obj.getPrice());
            ps.executeUpdate();
        } catch (SQLException e){
            System.out.println("SQLException: " + e.getMessage());
            System.out.println("SQLState: " + e.getSQLState());
            System.out.println("VendorError: " + e.getErrorCode());
        }
    }

    @Override
    public void update(Product Obj) {
        try {
            PreparedStatement ps = con.prepareStatement("UPDATE products SET " +
                    "name = '" + Obj.getName() + "'," +
                    "description = '" + Obj.getDescription() + "'," +
                    "classrec = '" + Obj.getClassRec() + "'," +
                    "rarity = '" + Obj.getRarity() + "'," +
                    "price = '" + Obj.getPrice() + "' " +
                    "WHERE id = '" + Obj.getId() + "';");
            ps.executeUpdate();
        } catch (SQLException e){
            System.out.println("SQLException: " + e.getMessage());
            System.out.println("SQLState: " + e.getSQLState());
            System.out.println("VendorError: " + e.getErrorCode());
        }
    }

    public List<Product> getByRarity(String rarity){
        List<Product> productList = new ArrayList<>();
        try{
            PreparedStatement ps = con.prepareStatement("SELECT * from products WHERE rarity='"+rarity+"';");
            ResultSet rs =  ps.executeQuery();
            while(rs.next()){
                Product item = new Product(
                        rs.getString("id"),
                        rs.getString("name"),
                        rs.getString("classrec"),
                        rs.getString("description"),
                        rs.getString("rarity"),
                        rs.getInt("price")
                );
                productList.add(item);
            }
        } catch(SQLException e){
            System.out.println("SQLException: " + e.getMessage());
            System.out.println("SQLState: " + e.getSQLState());
            System.out.println("VendorError: " + e.getErrorCode());
        }
        return productList;
    }
    @Override
    public void delete(String id) {
    try {
        PreparedStatement ps = con.prepareStatement("DELETE FROM products WHERE id= '"+id+"';");
        ps.executeUpdate();
    } catch (SQLException e){
        System.out.println("SQLException: " + e.getMessage());
        System.out.println("SQLState: " + e.getSQLState());
        System.out.println("VendorError: " + e.getErrorCode());
    }
    }

    @Override
    public Product getByID(String id) {
        Product prod = new Product();
        try {
            PreparedStatement ps = con.prepareStatement("SELECT * FROM products WHERE id='"+id+"';");
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                Product pro = new Product(
                        rs.getString("id"),
                        rs.getString("name"),
                        rs.getString("classrec"),
                        rs.getString("description"),
                        rs.getString("rarity"),
                        rs.getInt("price")
                );
                prod = pro;
            }
        } catch (SQLException e){
            System.out.println("SQLException: " + e.getMessage());
            System.out.println("SQLState: " + e.getSQLState());
            System.out.println("VendorError: " + e.getErrorCode());
        }
        return prod;
    }

    @Override
    public List<Product> getAll() {
        List<Product> products = new ArrayList<>();
       try{
           PreparedStatement ps = con.prepareStatement("SELECT * FROM products;");
           ResultSet rs = ps.executeQuery();
           while (rs.next()){
               Product prod = new Product(
                       rs.getString("id"),
                       rs.getString("name"),
                       rs.getString("classrec"),
                       rs.getString("description"),
                       rs.getString("rarity"),
                       rs.getInt("price")
                       );
               products.add(prod);
           }
       } catch (SQLException e){
           System.out.println("SQLException: " + e.getMessage());
           System.out.println("SQLState: " + e.getSQLState());
           System.out.println("VendorError: " + e.getErrorCode());
       }
       return products;
    }

    public List<Product> searchProductsByName(String search){
        List<Product> products = new ArrayList<>();
        try{
            PreparedStatement ps = con.prepareStatement("SELECT * FROM products WHERE name LIKE '%"+search+"%';");
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                Product prod = new Product(
                        rs.getString("id"),
                        rs.getString("name"),
                        rs.getString("classrec"),
                        rs.getString("description"),
                        rs.getString("rarity"),
                        rs.getInt("price")
                );
                products.add(prod);
            }
        } catch(SQLException e){
            System.out.println("SQLException: " + e.getMessage());
            System.out.println("SQLState: " + e.getSQLState());
            System.out.println("VendorError: " + e.getErrorCode());
        }
        return products;
    }
}
