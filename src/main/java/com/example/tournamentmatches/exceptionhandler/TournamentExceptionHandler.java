package com.example.tournamentmatches.exceptionhandler;

import com.example.tournamentmatches.exception.TournamentAlreadyStartedException;
import com.example.tournamentmatches.exception.TournamentNotFoundException;
import com.example.tournamentmatches.exception.TournamentOwnershipException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class TournamentExceptionHandler {


    @ExceptionHandler(TournamentNotFoundException.class)
    public ResponseEntity<String> handlerTournamentNotFoundException(TournamentNotFoundException exception) {
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(TournamentAlreadyStartedException.class)
    public ResponseEntity<String> handlerTournamentAlreadyStartedException(TournamentAlreadyStartedException exception) {
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(TournamentOwnershipException.class)
    public ResponseEntity<String> handlerTournamentOwnershipException(TournamentOwnershipException exception) {
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.NOT_FOUND);
    }


}
