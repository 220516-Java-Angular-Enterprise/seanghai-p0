package com.revature.hai_app.services;

import com.revature.hai_app.daos.inventInstDAO;
import com.revature.hai_app.models.Inventinstance;
import com.revature.hai_app.models.Store;

public class InventoryService {
    private static com.revature.hai_app.daos.inventInstDAO inventInstDAO;

    public InventoryService(inventInstDAO inventInstDAO){ this.inventInstDAO = inventInstDAO;}

    public String getStoreIDByItemID(String id){
        Inventinstance inventory = inventInstDAO.getByProdID(id);
        return inventory.getStore_id();
    }

    public int getProductCountByProdID(String id){
        Inventinstance inventory = inventInstDAO.getByProdID(id);
        return inventory.getProd_count();
    }

    public void updateInventoryCount(Inventinstance inventinstance){
        inventInstDAO.updateProdCount(inventinstance);
    }

    public Inventinstance getByProdID(String id){
        Inventinstance inventinstance = inventInstDAO.getByProdID(id);
        return inventinstance;
    }

}
