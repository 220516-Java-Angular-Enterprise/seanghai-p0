package com.revature.hai_app.services;

import com.revature.hai_app.models.Orderinstance;
import com.revature.hai_app.models.Orders;

import java.util.ArrayList;
import java.util.List;

public class OrderInstanceService {
    private final com.revature.hai_app.daos.orderInstDAO orderInstDAO;

    public OrderInstanceService(com.revature.hai_app.daos.orderInstDAO orderInstDAO) {
        this.orderInstDAO = orderInstDAO;
    }

    public void registerOrderInst(Orderinstance orderinstance){
        orderInstDAO.save(orderinstance);
    }

    public List<String> getOrdersByStoreID(String id){
        List<Orderinstance> orderInstances = orderInstDAO.getAllbyStoreID(id);
        List<String> orders = new ArrayList<>();
        for(Orderinstance ord:orderInstances){
            orders.add(ord.getOrder_id());
        }
        return orders;
    }

    public Orderinstance getByOrderID(String id){
        return orderInstDAO.getByOrderID(id);
    }
}
