package com.revature.hai_app.models;

public class Store {
    private String id;
    private String location;
    private String product_id;
    private String order_id;

    public Store(String id, String location, String product_id, String order_id) {
        this.id = id;
        this.location = location;
        this.product_id = product_id;
        this.order_id = order_id;
    }

    public Store() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getProduct_id() {
        return product_id;
    }

    public void setProduct_id(String product_id) {
        this.product_id = product_id;
    }

    public String getOrder_id() {
        return order_id;
    }

    public void setOrder_id(String order_id) {
        this.order_id = order_id;
    }
    @Override
    public String toString() {
        return "Store{" +
                "id='" + id + '\'' +
                ", location='" + location + '\'' +
                ", product_id='" + product_id + '\'' +
                ", order_id='" + order_id + '\'' +
                '}';
    }
}

