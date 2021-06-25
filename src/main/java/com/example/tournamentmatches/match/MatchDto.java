package com.example.tournamentmatches.match;

import com.example.tournamentmatches.tournament.Tournament;
import com.example.tournamentmatches.user.User;
import com.example.tournamentmatches.user.UserDto;
import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MatchDto {
    private Long id;
    private Long number;
    private UserDto firstParticipant;
    private UserDto secondParticipant;
    private Long tournamentId;
    private String tournamentTitle;
    private Long winner;

    public MatchDto(Match match) {
        this.id = match.getId();
        this.number = match.getNumber();
        if(match.getFirstParticipant() != null) {
            this.firstParticipant = new UserDto(match.getFirstParticipant());
        }
        if(match.getSecondParticipant() != null) {
            this.secondParticipant = new UserDto(match.getSecondParticipant());
        }
        this.tournamentId = match.getTournament().getId();
        this.tournamentTitle = match.getTournament().getTitle();
        this.winner = match.getWinner();
    }

    public UserDto getWinnerParticipant() {
        if (winner == 1) {
            return firstParticipant;
        } else if (winner == 2) {
            return secondParticipant;
        } else {
            return null;
        }
    }
}
