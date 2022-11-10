package com.fiba.shoppingservice.exception.exceptions;

import com.fiba.shoppingservice.enums.ErrorMessage;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author Tevfik Kadan
 * @created 10/11/2022 - 04:19
 */
@ResponseStatus(HttpStatus.NOT_FOUND)
public class ProductNotFoundException extends RuntimeException {
    public ProductNotFoundException(ErrorMessage message) {
        super(message.getMessage());
    }
}
