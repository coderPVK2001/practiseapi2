package com.practiseapi2.controller.Exception;

public class UpdateIdNotFoundException extends RuntimeException{

    public UpdateIdNotFoundException(String message) {
        super(message);
    }
}
