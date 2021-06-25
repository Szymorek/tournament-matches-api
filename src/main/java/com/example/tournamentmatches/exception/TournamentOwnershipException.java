package com.example.tournamentmatches.exception;

public class TournamentOwnershipException extends RuntimeException{
    private Long tournamentId;
    private Long userId;

    public TournamentOwnershipException(Long tournamentId, Long userId) {
        this.tournamentId = tournamentId;
        this.userId = userId;
    }

    @Override
    public String getMessage() {
        return "Tournament with id [ " + tournamentId + " ] was not created by user with id [ " + userId + " ]";
    }
}
