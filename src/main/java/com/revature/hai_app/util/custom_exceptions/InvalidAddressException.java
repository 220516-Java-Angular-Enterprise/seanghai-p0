package com.revature.hai_app.util.custom_exceptions;

public class InvalidAddressException extends RuntimeException{
    public InvalidAddressException(String message) {
        super(message);
    }
}
