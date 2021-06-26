package com.example.tournamentmatches.tournament;

import com.example.tournamentmatches.exception.TournamentAlreadyStartedException;
import com.example.tournamentmatches.exception.TournamentNotFoundException;
import com.example.tournamentmatches.exception.TournamentOwnershipException;
import com.example.tournamentmatches.match.Match;
import com.example.tournamentmatches.match.MatchRepository;
import com.example.tournamentmatches.user.User;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class TournamentService {
    private final TournamentRepository tournamentRepository;
    private final MatchRepository matchRepository;

    private List<Tournament> getAllTournaments() {
        return tournamentRepository.findAll();
    }

    public List<TournamentDto> getAllTournamentsDto() {
        return getAllTournaments()
                .stream()
                .map(TournamentDto::new)
                .collect(Collectors.toList());
    }

    public Tournament getTournamentById(Long id) {
        return tournamentRepository.findById(id)
                .orElseThrow(() -> new TournamentNotFoundException(id));
    }

    public Tournament createTournament(TournamentCreateDto tournamentCreateDto, User user) {
        Tournament tournament = convertToTournament(tournamentCreateDto, user);
        tournamentRepository.save(tournament);
        return tournament;
    }

    @Transactional
    public TournamentDto addParticipant(Long tournamentId, User user) {
        Tournament tournament = getTournamentById(tournamentId);
        int countOfParticipants = (int) tournament.getParticipants().stream().count();
        int maxParticipants = tournament.getPlaces();

        if ( countOfParticipants == maxParticipants ) {
            throw new TournamentAlreadyStartedException(tournamentId);
        }

        tournament.getParticipants().add(user);
        tournamentRepository.save(tournament);
        if (countOfParticipants+1 == maxParticipants) {
            startTournament(tournament);
        }
        return new TournamentDto(tournament);
    }

    private Tournament convertToTournament(TournamentCreateDto tournamentCreateDto, User user) {
        return new Tournament(
                tournamentCreateDto.getId(),
                tournamentCreateDto.getTitle(),
                tournamentCreateDto.getPlaces(),
                tournamentCreateDto.getPrize(),
                user
        );
    }

    public void removeTournament(Long id, User user) {
        Tournament tournament = getTournamentById(id);
        if (!tournament.getUser().getId().equals(user.getId())) {
            throw new TournamentOwnershipException(id, user.getId());
        }
        tournamentRepository.delete(tournament);
    }

    @Transactional
     void startTournament(Tournament tournament) {
        List<User> participants = new ArrayList<>(tournament.getParticipants());
        Collections.shuffle(participants);
        List<Match> matches = new ArrayList<>();
        for(int i = 0; i < tournament.getPlaces()-1; i++) {
            matches.add(new Match(
                    Long.valueOf(i+1),
                    null,
                    null,
                    tournament,
                    Long.valueOf(0)
            ));
            if (i < tournament.getPlaces()/2) {
                matches.get(i).setFirstParticipant(participants.get(i * 2));
                matches.get(i).setSecondParticipant(participants.get(i * 2 + 1));
            }
        }
        for(Match match : matches) {
            matchRepository.save(match);
        }
    }

    private List<Tournament> getAllCreatedTournaments(User user) {
        return tournamentRepository.findAllByUser(user);
    }

    public List<TournamentDto> getAllCreatedTournamentsDto(User user) {
        return getAllCreatedTournaments(user)
                .stream()
                .map(TournamentDto::new)
                .collect(Collectors.toList());
    }
}
