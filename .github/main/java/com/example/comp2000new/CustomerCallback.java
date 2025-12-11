package com.example.comp2000new;

public interface CustomerCallback {
    void onSuccess(Customer customer);
    void onError(String error);
}