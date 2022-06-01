package com.revature.hai_app.models;

public class Cart {
    private String order_id;
    private String product_id;
    private String cart_id;
    private int cart_count;
    private int cart_prodprice_total;
    private boolean checked_out = false;

    public Cart(String order_id, String product_id, String cart_id, int cart_count, int cart_prodprice_total, boolean checked_out) {
        this.order_id = order_id;
        this.product_id = product_id;
        this.cart_id = cart_id;
        this.cart_count = cart_count;
        this.cart_prodprice_total = cart_prodprice_total;
        this.checked_out = checked_out;
    }

    public Cart(String order_id, String cart_id, String product_id, boolean checked_out) {
        this.order_id = order_id;
        this.product_id = product_id;
        this.cart_id = cart_id;
        this.checked_out = checked_out;
    }

    public Cart(){};

    @Override
    public String toString() {
        return "Cart{" +
                "order_id='" + order_id + '\'' +
                ", product_id='" + product_id + '\'' +
                ", cart_id='" + cart_id + '\'' +
                ", cart_count=" + cart_count +
                ", cart_prodprice_total=" + cart_prodprice_total +
                ", checked_out=" + checked_out +
                '}';
    }

    public String getOrder_id() {
        return order_id;
    }

    public void setOrder_id(String order_id) {
        this.order_id = order_id;
    }

    public String getProduct_id() {
        return product_id;
    }

    public void setProduct_id(String product_id) {
        this.product_id = product_id;
    }

    public String getCart_id() {
        return cart_id;
    }

    public void setCart_id(String cart_id) {
        this.cart_id = cart_id;
    }

    public int getCart_count() {
        return cart_count;
    }

    public void setCart_count(int cart_count) {
        this.cart_count = cart_count;
    }

    public int getCart_prodprice_total() {
        return cart_prodprice_total;
    }

    public void setCart_prodprice_total(int cart_prodprice_total) {
        this.cart_prodprice_total = cart_prodprice_total;
    }

    public boolean isChecked_out() {
        return checked_out;
    }

    public void setChecked_out(boolean checked_out) {
        this.checked_out = checked_out;
    }
}
