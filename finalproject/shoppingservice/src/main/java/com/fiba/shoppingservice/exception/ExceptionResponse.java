package com.fiba.shoppingservice.exception;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * @author Tevfik Kadan
 * @created 10/11/2022 - 02:06
 */
public class ExceptionResponse {

    private String date;
    private String message;
    private String details;

    public ExceptionResponse(String message, String details) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        this.date = now.format(formatter);
        this.message = message;
        this.details = details;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }
}