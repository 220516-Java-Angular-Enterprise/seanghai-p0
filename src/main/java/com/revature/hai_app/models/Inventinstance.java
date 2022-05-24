package com.revature.hai_app.models;

public class Inventinstance {
    private String store_id;
    private String product_id;

    public Inventinstance(String store_id, String product_id) {
        this.store_id = store_id;
        this.product_id = product_id;
    }

    public Inventinstance() {
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

    @Override
    public String toString() {
        return "Inventinstance{" +
                "store_id='" + store_id + '\'' +
                ", product_id='" + product_id + '\'' +
                '}';
    }
}
