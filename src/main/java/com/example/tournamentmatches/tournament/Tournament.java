package com.example.tournamentmatches.tournament;

import com.example.tournamentmatches.match.Match;
import com.example.tournamentmatches.user.User;
import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Entity
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class Tournament {
    @SequenceGenerator(
            name = "tournament_sequence",
            sequenceName = "tournament_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "tournament_sequence"
    )
    @Id
    private Long id;
    @NotNull
    private String title;
    @NotNull
    private Integer places;
    @NotNull
    private Integer prize;

    @OneToMany(cascade = CascadeType.ALL, mappedBy="tournament")
    private Set<Match> matches;

    @NotNull
    @ManyToOne
    private User user;

    @ManyToMany
    private Set<User> participants;

    @ManyToMany
    private Set<User> judges;

    public Tournament(Long id, String title, Integer places, Integer prize, User user) {
        this.id = id;
        this.title = title;
        this.places = places;
        this.prize = prize;
        this.user = user;
    }
}
