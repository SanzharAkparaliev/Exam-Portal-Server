package com.example.examserver.helper;

import org.springframework.data.crossstore.ChangeSetPersister;

public class UserNotFoundException extends Exception {
    public UserNotFoundException() {
        super("User with this Username not found in database !! ");
    }
    public UserNotFoundException(String msg){
        super(msg);
    }
}
