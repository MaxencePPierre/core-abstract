package com.example.demo.errors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(ErrConflict.class)
    public final ResponseEntity<ErrorResponseBody> handleConflictError(ErrConflict ex) {
        ErrorResponseBody error = new ErrorResponseBody(ex.getMessage());
        return new ResponseEntity<>(error, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(ErrBadRequest.class)
    public final ResponseEntity<ErrorResponseBody> handleErrBadRequest(ErrBadRequest ex, WebRequest request) {
        ErrorResponseBody error = new ErrorResponseBody(ex.getMessage());
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

}