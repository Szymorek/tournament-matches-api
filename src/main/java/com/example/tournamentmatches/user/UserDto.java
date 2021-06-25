package com.example.tournamentmatches.user;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class UserDto {
    @NotNull
    private Long id;
    @NotNull
    private String username;
    @NotNull
    private String email;
    @NotNull
    private String name;
    @NotNull
    private String surname;

    public UserDto(User user) {
        this.id = user.getId();
        this.username = user.getUsername();
        this.email = user.getEmail();
        this.name = user.getName();
        this.surname = user.getSurname();
    }
}
