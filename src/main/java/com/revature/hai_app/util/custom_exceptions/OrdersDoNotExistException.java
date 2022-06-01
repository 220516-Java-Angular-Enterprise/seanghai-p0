package com.revature.hai_app.util.custom_exceptions;

public class OrdersDoNotExistException extends RuntimeException{
    public OrdersDoNotExistException(String message) {
        super(message);
    }
}
