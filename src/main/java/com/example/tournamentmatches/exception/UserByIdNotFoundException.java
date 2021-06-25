package com.example.tournamentmatches.exception;

public class UserByIdNotFoundException extends RuntimeException{
    private Long id;

    public UserByIdNotFoundException(Long userId) {
        this.id = userId;
    }

    @Override
    public String getMessage() {
        return "User with id [ " + id + " ] not found";
    }
}
