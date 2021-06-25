package com.example.tournamentmatches.tournament;

import com.example.tournamentmatches.match.MatchService;
import com.example.tournamentmatches.user.User;
import com.example.tournamentmatches.user.UserDto;
import com.example.tournamentmatches.user.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;


@RestController
@RequestMapping("api/v1/tournaments")
@AllArgsConstructor
public class TournamentController {
    private final TournamentService tournamentService;

    @GetMapping
    public List<TournamentDto> getAllTournamentsDto() {
        return tournamentService.getAllTournamentsDto();
    }

    @GetMapping(path = "manager")
    public List<TournamentDto> getAllCreatedTournamentsDto(@AuthenticationPrincipal User user) {
        return tournamentService.getAllCreatedTournamentsDto(user);
    }

    @PostMapping()
    public ResponseEntity<TournamentDto> createTournament(@RequestBody TournamentCreateDto tournamentCreateDto, @AuthenticationPrincipal User user) {
        Tournament createdTournament = tournamentService.createTournament(tournamentCreateDto, user);

        return ResponseEntity.ok()
                .body(new TournamentDto(createdTournament));
    }

    @PutMapping("{tournamentId}")
    public ResponseEntity<TournamentDto> joinTournament(@PathVariable Long tournamentId, @AuthenticationPrincipal User user) {
        return ResponseEntity.ok()
                .body(tournamentService.addParticipant(tournamentId, user));
    }

    @DeleteMapping("{tournamentId}")
    public ResponseEntity<Void> deleteTournament(@PathVariable Long tournamentId, @AuthenticationPrincipal User user) {
        tournamentService.removeTournament(tournamentId, user);
        return ResponseEntity.ok()
                .body(null);
    }
}
