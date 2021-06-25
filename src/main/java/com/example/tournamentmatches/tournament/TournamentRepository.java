package com.example.tournamentmatches.tournament;

import com.example.tournamentmatches.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TournamentRepository extends JpaRepository<Tournament, Long> {
    Optional<Tournament> findTournamentByTitle(String title);
    Optional<Tournament> findTournamentById(Long id);
    List<Tournament> findAllByUser(User user);
    Optional<Tournament> findAllByParticipantsContaining(User participant);
}
