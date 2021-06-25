package com.example.tournamentmatches.match;


import com.example.tournamentmatches.exception.TournamentNotFoundException;
import com.example.tournamentmatches.exception.TournamentOwnershipException;
import com.example.tournamentmatches.tournament.TournamentDto;
import com.example.tournamentmatches.user.User;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class MatchService {
    private final MatchRepository matchRepository;

    private List<Match> getAllMatches() {
        return matchRepository.findAll();
    }

    public List<MatchDto> getAllMatchesDto() {
        return getAllMatches()
                .stream()
                .map(MatchDto::new)
                .collect(Collectors.toList());
    }

    public MatchDto setWinner(Long matchId, Long winner, User user) {
        Match match = matchRepository.findById(matchId)
                .orElseThrow(() -> new IllegalStateException(
                        "match with id " + matchId + " does not exists"
                ));

        if (match.getTournament().getUser().getId() != user.getId()) {
            throw new TournamentOwnershipException(match.getTournament().getId(), user.getId());
        }
        match.setWinner(winner);
        matchRepository.save(match);


        if (match.getNumber()  < match.getTournament().getPlaces() - 1) {

            Long nextNumber = match.getNumber()/2 + match.getNumber()%2 + match.getTournament().getPlaces()/2;

            Match nextMatch = matchRepository.findByTournamentAndNumber(match.getTournament(), nextNumber)
                    .orElseThrow(() -> new TournamentNotFoundException(match.getTournament().getId()));
            if (match.getNumber()%2 == 1) {
                nextMatch.setFirstParticipant(match.getWinnerParticipant());
            } else {
                nextMatch.setSecondParticipant(match.getWinnerParticipant());
            }

            matchRepository.save(nextMatch);
            return new MatchDto(nextMatch);
        }
        return new MatchDto(match);
    }

    public List<MatchDto> getMatchHistory(User user) {
        return matchRepository.findByParticipant(user)
                .stream()
                .map(MatchDto::new)
                .collect(Collectors.toList());
    }
}
