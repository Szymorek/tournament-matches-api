package com.example.tournamentmatches.exception;

public class UserByEmailNotFoundException extends RuntimeException{
    private String email;

    public UserByEmailNotFoundException(String email) {
        this.email = email;
    }

    @Override
    public String getMessage() {
        return "User with email [ " + email + " ] not found";
    }
}
