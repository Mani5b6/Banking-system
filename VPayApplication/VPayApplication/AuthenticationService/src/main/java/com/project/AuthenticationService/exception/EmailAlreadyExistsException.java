package com.project.AuthenticationService.exception;

public class EmailAlreadyExistsException extends Exception{
    public EmailAlreadyExistsException(String message)
    {
        super(message);
    }
}
