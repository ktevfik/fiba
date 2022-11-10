package com.fiba.ecommerce.exception.exceptions;

import com.fiba.ecommerce.enums.ErrorMessage;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author Tevfik Kadan
 * @created 08/11/2022 - 04:10
 */
@ResponseStatus(HttpStatus.NOT_FOUND)
public class CategoryNotFoundException extends RuntimeException {

    public CategoryNotFoundException(ErrorMessage message) {
        super(message.getMessage());
    }

}
