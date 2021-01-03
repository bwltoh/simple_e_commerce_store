package com.example.e_commerce_store.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Category {

//part of categoryresponse

    private int id;
    private String name;
    private String description;
    @SerializedName("products")
    private List<Product> productList;
   /* private String category;
    private List<Product> productList;*/

    // Constructor of the class
    // to initialize the variables


    public Category(int id, String name, String description, List<Product> productList) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.productList = productList;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Product> getProductList() {
        return productList;
    }

    public void setProductList(List<Product> productList) {
        this.productList = productList;
    }
}
