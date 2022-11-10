package com.fiba.ecommerce.models;

/**
 * @author Tevfik Kadan
 * @created 10/11/2022 - 13:45
 */
public class SuccessMessage {

    private String message;

    public SuccessMessage() {

    }

    public SuccessMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return "SUCCESS";
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
