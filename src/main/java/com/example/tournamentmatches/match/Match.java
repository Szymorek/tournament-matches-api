package com.example.tournamentmatches.match;

import com.example.tournamentmatches.tournament.Tournament;
import com.example.tournamentmatches.user.User;
import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Optional;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Match {
    @SequenceGenerator(
            name = "match_sequence",
            sequenceName = "match_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "match_sequence"
    )
    @Id
    private Long id;
    private Long number;
    @ManyToOne
    private User firstParticipant;
    @ManyToOne
    private User secondParticipant;
    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tournament_id")
    private Tournament tournament;

    private Long winner;

    public Match(Long number, User firstParticipant, User secondParticipant, Tournament tournament, Long winner) {
        this.number = number;
        this.firstParticipant = firstParticipant;
        this.secondParticipant = secondParticipant;
        this.tournament = tournament;
        this.winner = winner;
    }

    public User getWinnerParticipant() {
        if (this.winner == 1) {
            return this.firstParticipant;
        } else if (this.winner == 2) {
            return this.secondParticipant;
        } else {
            return null;
        }
    }
}
