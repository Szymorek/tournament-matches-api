package com.example.tournamentmatches.tournament;

import com.example.tournamentmatches.match.MatchDto;
import com.example.tournamentmatches.user.User;
import com.example.tournamentmatches.user.UserDto;
import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Getter @Setter @NoArgsConstructor
public class TournamentDto {
    private Long id;
    private String title;
    private Integer places;
    private Integer prize;
    private UserDto user;
    private List<MatchDto> matches;
    private List<UserDto> participants;
    private List<UserDto> judges;


    public TournamentDto(Tournament tournament) {
        this.id = tournament.getId();
        this.title = tournament.getTitle();
        this.places = tournament.getPlaces();
        this.prize = tournament.getPrize();
        this.user = new UserDto(tournament.getUser());
        this.matches = Optional.ofNullable(tournament.getMatches())
                .orElseGet(Collections::emptySet)
                .stream()
                .map(MatchDto::new)
                .collect(Collectors.toList());
        this.participants = Optional.ofNullable(tournament.getParticipants())
                .orElseGet(Collections::emptySet)
                .stream()
                .map(UserDto::new)
                .collect(Collectors.toList());
        this.judges = Optional.ofNullable(tournament.getJudges())
                .orElseGet(Collections::emptySet)
                .stream()
                .map(UserDto::new)
                .collect(Collectors.toList());
    }
}
