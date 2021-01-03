package com.example.e_commerce_store.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Cart {


    private boolean status;
    private String errorNumber;
    private String msg;
    /* response come with key data and user object or with key error with string value
     * retrofit will use data or error according to response formate*/
    @SerializedName("data")
    private List<CartItem> cartItemList;
    private String message;//if cart was empty api send string rather than cart items

    public Cart() {
    }

    public Cart(boolean status, String msg, String errorNumber) {
        this.status = status;
        this.errorNumber = errorNumber;
        this.msg = msg;
    }

    public List<CartItem> getCartItemList() {
        return cartItemList;
    }

    public void setCartItemList(List<CartItem> cartItemList) {
        this.cartItemList = cartItemList;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getErrorNumber() {
        return errorNumber;
    }

    public void setErrorNumber(String errorNumber) {
        this.errorNumber = errorNumber;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}

