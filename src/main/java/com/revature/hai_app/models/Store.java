package com.revature.hai_app.models;

public class Store {
    private String id;
    private String location;

// Constructors
    public Store(String id, String location) {
        this.id = id;
        this.location = location;
    }

    public Store() {
    }
// Gets & Sets
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


    @Override
    public String toString() {
        return "Store{" +
                "id='" + id + '\'' +
                ", location='" + location + '\'' +
                '}';
    }
}

