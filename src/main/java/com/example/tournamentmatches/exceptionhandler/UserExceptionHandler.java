package com.example.tournamentmatches.exceptionhandler;

import com.example.tournamentmatches.exception.TournamentNotFoundException;
import com.example.tournamentmatches.exception.UserByEmailNotFoundException;
import com.example.tournamentmatches.exception.UserByIdNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class UserExceptionHandler {

    @ExceptionHandler(UserByIdNotFoundException.class)
    public ResponseEntity<String> handlerUserByIdNotFoundException(UserByIdNotFoundException exception) {
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(UserByEmailNotFoundException.class)
    public ResponseEntity<String> handlerUserByEmailNotFoundException(UserByEmailNotFoundException exception) {
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.NOT_FOUND);
    }
}
