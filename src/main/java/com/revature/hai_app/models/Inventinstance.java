package com.revature.hai_app.models;

public class Inventinstance {
    private String store_id;
    private String product_id;
    private int prod_count;

    public Inventinstance(String store_id, String product_id, int prod_count) {
        this.store_id = store_id;
        this.product_id = product_id;
        this.prod_count = prod_count;
    }
    public Inventinstance(){};

    @Override
    public String toString() {
        return "Inventinstance{" +
                "store_id='" + store_id + '\'' +
                ", product_id='" + product_id + '\'' +
                ", prod_count=" + prod_count +
                '}';
    }

    public String getStore_id() {
        return store_id;
    }

    public void setStore_id(String store_id) {
        this.store_id = store_id;
    }

    public String getProduct_id() {
        return product_id;
    }

    public void setProduct_id(String product_id) {
        this.product_id = product_id;
    }

    public int getProd_count() {
        return prod_count;
    }

    public void setProd_count(int prod_count) {
        this.prod_count = prod_count;
    }
}
