package com.example.tournamentmatches.match;

import com.example.tournamentmatches.tournament.Tournament;
import com.example.tournamentmatches.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

import static org.hibernate.loader.Loader.SELECT;

@Repository
public interface MatchRepository extends JpaRepository<Match, Long> {

    Optional<Match> findByTournamentAndNumber(Tournament tournament, Long number);

    @Query("SELECT m FROM Match m WHERE m.firstParticipant = ?1 OR m.secondParticipant = ?1")
    List<Match> findByParticipant(User user);
}
