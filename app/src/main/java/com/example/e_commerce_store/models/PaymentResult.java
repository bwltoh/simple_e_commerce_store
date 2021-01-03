package com.example.e_commerce_store.models;

public class PaymentResult {
//response for payment process final result success or failed

    /* private boolean status;
    private String errorNumber;
    private String msg;*/
  private  boolean success;

  private  Transaction transaction;

    public boolean isSuccess() {
        return success;
    }

    public Transaction getTransaction() {
        return transaction;
    }
}
