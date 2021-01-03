package com.example.e_commerce_store.models;

public class OrderResponse {
    //for payment process
    private boolean status;
    private String errorNumber;
    private String msg;
    private int order_id;
   // private String msg;
    /*{
    "order_id": 9,
    "msg": "data inserted"
}*/

    public OrderResponse(boolean status, String msg,String errorNumber ) {
        this.status = status;
        this.errorNumber = errorNumber;
        this.msg = msg;
    }

    public int getOrder_id() {
        return order_id;
    }

    public String getMsg() {
        return msg;
    }

    public boolean isStatus() {
        return status;
    }

    public String getErrorNumber() {
        return errorNumber;
    }
}
