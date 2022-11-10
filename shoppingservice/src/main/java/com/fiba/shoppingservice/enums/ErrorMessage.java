package com.fiba.shoppingservice.enums;

/**
 * @author Tevfik Kadan
 * @created 10/11/2022 - 02:07
 */
public enum ErrorMessage {

    SOMETHING_WENT_WRONG("SOMETHING_WENT_WRONG"),
    SOMETHING_WENT_WRONG_WITH_MESSAGE("Something went wrong, please try again later"),

    CART_NOT_FOUND("CART_NOT_FOUND"),
    CART_NOT_FOUND_MESSAGE("Cart not found with given id, check your cart id"),
    CUSTOMER_ALREADY_EXIST("CART_ALREADY_EXIST_WITH_THIS_CUSTOMER_NAME"),
    CUSTOMER_ALREADY_EXIST_MESSAGE("Customer already exist with this name, check your customer name"),

    CART_PRODUCT_NOT_FOUND("CART_PRODUCT_NOT_FOUND"),
    CART_PRODUCT_NOT_FOUND_MESSAGE("Cart product not found with given id, check your cart product id"),

    PRODUCT_NOT_FOUND("PRODUCT_NOT_FOUND"),
    PRODUCT_NOT_FOUND_MESSAGE("Product not found with given id, check your product id"),
    ;

    private final String message;

    ErrorMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    @Override
    public String toString() {
        return message;
    }
}