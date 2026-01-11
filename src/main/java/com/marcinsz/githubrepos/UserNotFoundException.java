package com.marcinsz.githubrepos;

public class UserNotFoundException extends RuntimeException {
    UserNotFoundException(String message){
        super(message);
    }
}
