package com.example.demo.errors;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class ErrBadRequest extends RuntimeException {
    public ErrBadRequest(String message) {
        super(message);
    }
}

