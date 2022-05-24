package com.revature.hai_app.models;

import java.awt.*;

public class Orders {
    private String id;
    private String date;
    private int price_total;
    private int product_qty;
    private String user_id;
    private String store_id;

    public Orders(String id, String date, int price_total, int product_qty, String user_id, String store_id) {
        this.id = id;
        this.date = date;
        this.price_total = price_total;
        this.product_qty = product_qty;
        this.user_id = user_id;
        this.store_id = store_id;
    }

    public Orders() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getPrice_total() {
        return price_total;
    }

    public void setPrice_total(int price_total) {
        this.price_total = price_total;
    }

    public int getProduct_qty() {
        return product_qty;
    }

    public void setProduct_qty(int product_qty) {
        this.product_qty = product_qty;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getStore_id() {
        return store_id;
    }

    public void setStore_id(String store_id) {
        this.store_id = store_id;
    }

    @Override
    public String toString() {
        return "Orders{" +
                "id='" + id + '\'' +
                ", date='" + date + '\'' +
                ", price_total=" + price_total +
                ", product_qty=" + product_qty +
                ", user_id='" + user_id + '\'' +
                ", store_id='" + store_id + '\'' +
                '}';
    }
}
