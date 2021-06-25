package com.example.tournamentmatches.tournament;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class TournamentCreateDto {
    private Long id;
    @NotNull
    private String title;
    @NotNull
    private Integer places;
    @NotNull
    private Integer prize;
}
