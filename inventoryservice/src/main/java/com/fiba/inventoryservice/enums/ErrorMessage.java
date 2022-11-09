package com.fiba.inventoryservice.enums;

/**
 * @author Tevfik Kadan
 * @created 08/11/2022 - 04:11
 */
public enum ErrorMessage {

    SOMETHING_WENT_WRONG("SOMETHING_WENT_WRONG"),
    CATEGORY_NOT_FOUND("CATEGORY_NOT_FOUND"),
    CATEGORY_ALREADY_EXIST("CATEGORY_ALREADY_EXIST"),
    PRODUCT_NOT_FOUND("PRODUCT_NOT_FOUND"),

    PRODUCT_ALREADY_EXIST("PRODUCT_ALREADY_EXIST");

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
