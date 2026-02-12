package com.project.AccountService.exception;

public class UserNotFoundException extends Exception{
    public UserNotFoundException(String message)
    {
        super(message);
    }
}
