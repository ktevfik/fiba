package com.fiba.inventoryservice.enums;

/**
 * @author Tevfik Kadan
 * @created 08/11/2022 - 04:11
 */
public enum ErrorMessage {

    SOMETHING_WENT_WRONG("SOMETHING_WENT_WRONG"),
    SOMETHING_WENT_WRONG_WITH_MESSAGE("Something went wrong, please try again later"),
    CATEGORY_NOT_FOUND("CATEGORY_NOT_FOUND"),
    CATEGORY_NOT_FOUND_MESSAGE("Category not found with given id, check your category id"),
    CATEGORY_ALREADY_EXIST("CATEGORY_ALREADY_EXIST"),
    CATEGORY_ALREADY_EXIST_MESSAGE("Category already exist with this name, check your category name"),
    PRODUCT_NOT_FOUND("PRODUCT_NOT_FOUND"),
    PRODUCT_NOT_FOUND_MESSAGE("Product not found with given id, check your product id"),

    PRODUCT_ALREADY_EXIST("PRODUCT_ALREADY_EXIST"),
    PRODUCT_ALREADY_EXIST_MESSAGE("Product already exist with this name, check your product name");

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
