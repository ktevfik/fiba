package com.fiba.ecommerce.exception.exceptions;

import com.fiba.ecommerce.enums.ErrorMessage;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author Tevfik Kadan
 * @created 10/11/2022 - 02:17
 */
@ResponseStatus(HttpStatus.NOT_FOUND)
public class CartNotFoundException extends RuntimeException {

    public CartNotFoundException(ErrorMessage message) {
        super(message.getMessage());
    }
}
