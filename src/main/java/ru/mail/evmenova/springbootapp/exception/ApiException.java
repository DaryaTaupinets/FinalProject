package ru.mail.evmenova.springbootapp.exception;

import org.springframework.http.HttpStatus;

public class ApiException extends RuntimeException {
    private HttpStatus status;

    public ApiException(HttpStatus status) {
        this.status = status;
    }

    public ApiException(String message, HttpStatus status) {
        super(message);
        this.status = status;
    }

    public ApiException(String message, Throwable cause, HttpStatus status) {
        super(message, cause);
        this.status = status;
    }

    public ApiException(Throwable cause, HttpStatus status) {
        super(cause);
        this.status = status;
    }

    public ApiException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace,
            HttpStatus status) {
        super(message, cause, enableSuppression, writableStackTrace);
        this.status = status;
    }

    public HttpStatus getStatus() {
        return status;
    }
}
