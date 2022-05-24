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

    }

    @Override
    public void update(Store Obj) {

    }

    @Override
    public void delete(String id) {

    }

    @Override
    public Store getByID(String id) {
        Store stor = new Store();
        try {
            PreparedStatement ps = con.prepareStatement("SELECT * FROM stores WHERE id='"+id+"'");
            ResultSet rs = ps.executeQuery();
            while(rs.next()) {
                Store sto = new Store(
                        rs.getString("id"),
                        rs.getString("location"),
                        rs.getString("product_id"),
                        rs.getString("order_id")
                );
                stor = sto;
            }
        } catch (SQLException e) {
            throw new RuntimeException("An error occurred when trying to save to database.");
        }
        return stor;
    }

    @Override
    public List<Store> getAll() {
        List<Store> stores = new ArrayList<>();

        try {
            PreparedStatement ps = con.prepareStatement("SELECT * FROM stores");
            ResultSet rs = ps.executeQuery();

            while(rs.next()){
                Store store = new Store(
                        rs.getString("id"),
                        rs.getString("location"),
                        rs.getString("product_id"),
                        rs.getString("order_id")

                );
                stores.add(store);
            }
        } catch (SQLException e) {
            throw new RuntimeException("An error occurred when tyring to get data from to the database.");
        }
        return stores;
    }
}
