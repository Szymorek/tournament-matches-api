package com.example.tournamentmatches.exception;

public class TournamentAlreadyStartedException extends RuntimeException{
    private Long id;

    public TournamentAlreadyStartedException(Long tournamentId) {
        this.id = tournamentId;
    }

    @Override
    public String getMessage() {
        return "Tournament with [ " + id + " ] already started";
    }
}
