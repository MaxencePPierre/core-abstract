package com.example.demo.errors;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class ErrConflict extends RuntimeException {
    public ErrConflict(String message) {
        super(message);
    }
}