package com.revature.hai_app.services;

import com.revature.hai_app.daos.cartDAO;
import com.revature.hai_app.models.Cart;

import java.awt.*;
import java.sql.Array;
import java.util.*;
import java.util.List;

public class CartService {
    private final com.revature.hai_app.daos.cartDAO cartDAO;
    public CartService(cartDAO cartDAO){this.cartDAO = cartDAO;}

    public void registerCart(Cart cart){
        cartDAO.save(cart);
    }

    public void updateCart(Cart cart){
        cartDAO.update(cart);
    }


    public List<String> getProductIDsByCartID(String id){
        List<Cart> carts = (List<Cart>) cartDAO.getCartsByID(id);
        List<String> prodIDArray = new ArrayList<>();

        for(Cart cart:carts){
            if(!cart.getProduct_id().equals("NULL")) prodIDArray.add(cart.getProduct_id());
        }
        return prodIDArray;
    }

    public void deleteCart(String id){
        cartDAO.delete(id);
    }

    public void deleteProdFromCart(String cartID, String prodID){
        cartDAO.removeItemByCartProdID(cartID, prodID);
    }

    public List<String> getProductIDsByOrderID(String id){
        List<String> prodIDs = cartDAO.getProdIDsByOrderID(id);
        return prodIDs;
    }


}
