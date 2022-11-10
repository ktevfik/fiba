package com.fiba.ecommerce.exception;

import com.fiba.ecommerce.enums.ErrorMessage;
import com.fiba.ecommerce.exception.exceptions.CategoryAlreadyExistException;
import com.fiba.ecommerce.exception.exceptions.CategoryNotFoundException;
import com.fiba.ecommerce.exception.exceptions.ProductAlreadyExistException;
import com.fiba.ecommerce.exception.exceptions.ProductNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/**
 * @author Tevfik Kadan
 * @created 08/11/2022 - 04:07
 */
@RestController
@ControllerAdvice
public class CustomizedResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(Exception.class)
    public final ResponseEntity<Object> handleAllExceptions(Exception ex, WebRequest request) {
        ExceptionResponse exceptionResponse = new ExceptionResponse(ErrorMessage.SOMETHING_WENT_WRONG.getMessage(), ErrorMessage.SOMETHING_WENT_WRONG_WITH_MESSAGE.getMessage());
        return new ResponseEntity<>(exceptionResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(CategoryNotFoundException.class)
    public final ResponseEntity<Object> handleCategoryNotFoundExceptions(CategoryNotFoundException ex, WebRequest request) {
        ExceptionResponse exceptionResponse = new ExceptionResponse(ex.getMessage(), ErrorMessage.CATEGORY_NOT_FOUND_MESSAGE.getMessage());
        return new ResponseEntity<>(exceptionResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(CategoryAlreadyExistException.class)
    public final ResponseEntity<Object> handleCategoryAlreadyExistExceptions(CategoryAlreadyExistException ex, WebRequest request) {
        ExceptionResponse exceptionResponse = new ExceptionResponse(ex.getMessage(), ErrorMessage.CATEGORY_ALREADY_EXIST_MESSAGE.getMessage());
        return new ResponseEntity<>(exceptionResponse, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(ProductNotFoundException.class)
    public final ResponseEntity<Object> handleProductNotFoundExceptions(ProductNotFoundException ex, WebRequest request) {
        ExceptionResponse exceptionResponse = new ExceptionResponse(ex.getMessage(), ErrorMessage.PRODUCT_NOT_FOUND_MESSAGE.getMessage());
        return new ResponseEntity<>(exceptionResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ProductAlreadyExistException.class)
    public final ResponseEntity<Object> handleProductAlreadyExistExceptions(ProductAlreadyExistException ex, WebRequest request) {
        ExceptionResponse exceptionResponse = new ExceptionResponse(ex.getMessage(), ErrorMessage.PRODUCT_ALREADY_EXIST_MESSAGE.getMessage());
        return new ResponseEntity<>(exceptionResponse, HttpStatus.CONFLICT);
    }
}
