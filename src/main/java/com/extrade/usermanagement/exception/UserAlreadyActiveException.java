package com.extrade.usermanagement.exception;

public class UserAlreadyActiveException extends RuntimeException{
    public UserAlreadyActiveException(String message) {
        super(message);
    }
}
