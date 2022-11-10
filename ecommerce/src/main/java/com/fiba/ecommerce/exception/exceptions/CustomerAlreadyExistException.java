package com.fiba.ecommerce.exception.exceptions;

import com.fiba.ecommerce.enums.ErrorMessage;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author Tevfik Kadan
 * @created 10/11/2022 - 02:08
 */
@ResponseStatus(HttpStatus.CONFLICT)
public class CustomerAlreadyExistException extends RuntimeException {

    public CustomerAlreadyExistException(ErrorMessage message) {
        super(message.getMessage());
    }

}
