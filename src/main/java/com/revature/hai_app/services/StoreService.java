package com.revature.hai_app.services;

import com.revature.hai_app.daos.storeDAO;
import com.revature.hai_app.models.Product;
import com.revature.hai_app.models.Store;

import java.util.List;

public class StoreService {
    private static com.revature.hai_app.daos.storeDAO storeDAO;
    public StoreService(storeDAO storeDAO){this.storeDAO = storeDAO;}

    public String getLocationByStoreID(String id) {
        Store store = storeDAO.getByID(id);
        return store.getLocation();
    }

    public Store getByStoreLocation(String location){
        Store store = storeDAO.getByLocation(location);
        return store;
    }

}
