package com.fiba.inventoryservice.exception.exceptions;

import com.fiba.inventoryservice.enums.ErrorMessage;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author Tevfik Kadan
 * @created 08/11/2022 - 04:45
 */
@ResponseStatus(HttpStatus.BAD_REQUEST)
public class CategoryAlreadyExistException extends RuntimeException {

    public CategoryAlreadyExistException(ErrorMessage message) {
        super(message.getMessage());
    }
}
