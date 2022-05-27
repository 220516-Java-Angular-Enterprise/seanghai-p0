package com.revature.hai_app.models;

public class Product {
    private String id;
    private String name;
    private String classRec;
    private String description;
    private String rarity;
    private int price;


    public Product(String id, String name, String classRec, String description, String rarity, int price) {
        this.id = id;
        this.name = name;
        this.classRec = classRec;
        this.description = description;
        this.rarity = rarity;
        this.price = price;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", classRec='" + classRec + '\'' +
                ", description='" + description + '\'' +
                ", rarity='" + rarity + '\'' +
                ", price=" + price + '\'' +
                '}';
    }

    public Product() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getClassRec() {
        return classRec;
    }

    public void setClassRec(String classRec) {
        this.classRec = classRec;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getRarity() {
        return rarity;
    }

    public void setRarity(String rarity) {
        this.rarity = rarity;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }



}
