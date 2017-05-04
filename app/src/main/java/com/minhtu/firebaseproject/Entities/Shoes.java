package com.minhtu.firebaseproject.Entities;

/**
 * Created by minhtu on 5/1/17.
 */

public class Shoes {


    private String shoesID;
    private String name;
    private String description;
    private float price;
    private String brand;
    private String imageUrl;

    public Shoes(){

    }

    public Shoes(String shoesID, String name, String description, float price, String brand, String imageUrl) {
        this.shoesID = shoesID;
        this.name = name;
        this.description = description;
        this.price = price;
        this.brand = brand;
        this.imageUrl = imageUrl;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public float getPrice() {
        return price;
    }

    public String getBrand() {
        return brand;
    }

    public void setShoesID(String shoesID) {
        this.shoesID = shoesID;
    }

    public String getShoesID() {

        return shoesID;
    }

}
