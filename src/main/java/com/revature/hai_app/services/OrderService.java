package com.revature.hai_app.services;

import com.revature.hai_app.models.Orderinstance;
import com.revature.hai_app.models.Orders;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OrderService {
    private final com.revature.hai_app.daos.ordersDAO ordersDAO;

    public OrderService(com.revature.hai_app.daos.ordersDAO ordersDAO) {
        this.ordersDAO = ordersDAO;
    }

    public void deleteOrder(String id){
        ordersDAO.delete(id);
    }

    public void registerOrder(Orders order){
        ordersDAO.save(order);
    }

    public void updateOrder(Orders order){
        ordersDAO.update(order);
    }

    public List<Orders> getOrdersByUserID(String id){
        List<Orders> orderHist = ordersDAO.getAllByUserId(id);
        return orderHist;
    }

    public Orders getByOrderID(String id) {
       return ordersDAO.getByID(id);
    }


}
