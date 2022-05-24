package com.revature.hai_app.util.custom_exceptions;

public class InvalidStateException extends RuntimeException{
    public InvalidStateException(String message) {
        super(message);
    }
}
