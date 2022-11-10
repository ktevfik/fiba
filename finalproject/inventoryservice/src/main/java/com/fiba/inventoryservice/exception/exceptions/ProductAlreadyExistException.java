package com.fiba.inventoryservice.exception.exceptions;

import com.fiba.inventoryservice.enums.ErrorMessage;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author Tevfik Kadan
 * @created 09/11/2022 - 00:06
 */
@ResponseStatus(HttpStatus.CONFLICT)
public class ProductAlreadyExistException extends RuntimeException {

    public ProductAlreadyExistException(ErrorMessage message) {
        super(message.getMessage());
    }

}
