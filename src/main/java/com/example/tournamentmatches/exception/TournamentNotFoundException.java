package com.example.tournamentmatches.exception;

public class TournamentNotFoundException extends RuntimeException{
    private Long id;

    public TournamentNotFoundException(Long tournamentId) {
        this.id = tournamentId;
    }

    @Override
    public String getMessage() {
        return "Tournament with [ " + id + " ] not found";
    }
}
