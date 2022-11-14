package com.petproject.portfolio.exception;

import org.springframework.http.HttpStatus;

public class Error {
    private final String message;
    private final HttpStatus status;
    private final int code;

    Error(String message, HttpStatus status) {
        this.message = message;
        this.status = status;
        this.code = status.value();
    }

    public String getMessage() {
        return message;
    }

    public HttpStatus getStatus() {
        return status;
    }

    public int getCode() {
        return code;
    }
}
