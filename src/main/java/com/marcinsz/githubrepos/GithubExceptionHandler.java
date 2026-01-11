package com.marcinsz.githubrepos;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GithubExceptionHandler {

    @ExceptionHandler(UserNotFoundException.class)
    ResponseEntity<ErrorResponse> handleUserNotFound(UserNotFoundException ex) {
        ErrorResponse error = new ErrorResponse(
                404, ex.getMessage()
        );
        return new ResponseEntity<>(error, org.springframework.http.HttpStatus.NOT_FOUND);
    }
}
