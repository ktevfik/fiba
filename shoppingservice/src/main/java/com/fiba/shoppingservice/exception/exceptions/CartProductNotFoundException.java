package com.fiba.shoppingservice.exception.exceptions;

import com.fiba.shoppingservice.enums.ErrorMessage;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author Tevfik Kadan
 * @created 10/11/2022 - 03:21
 */
@ResponseStatus(HttpStatus.NOT_FOUND)
public class CartProductNotFoundException extends RuntimeException {
    public CartProductNotFoundException(ErrorMessage message) {
        super(message.getMessage());
    }

}
