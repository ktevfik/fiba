package com.fiba.inventoryservice.enums;

/**
 * @author Tevfik Kadan
 * @created 08/11/2022 - 04:11
 */
public enum ErrorMessage {
    CATEGORY_NOT_FOUND("Category not found"),
    CATEGORY_ALREADY_EXIST("Category already exist");

    private String message;

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
