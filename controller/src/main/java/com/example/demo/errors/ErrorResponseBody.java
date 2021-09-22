package com.example.demo.errors;

public class ErrorResponseBody {
    private String message;

    public ErrorResponseBody(String message) {
        super();
        this.message = message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}