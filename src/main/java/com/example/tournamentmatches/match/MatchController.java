package com.example.tournamentmatches.match;

import com.example.tournamentmatches.tournament.TournamentDto;
import com.example.tournamentmatches.tournament.TournamentService;
import com.example.tournamentmatches.user.User;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/matches")
@AllArgsConstructor
public class MatchController {
    private final MatchService matchService;

    @PutMapping("{matchId}/{winner}")
    public ResponseEntity<MatchDto> setWinner(@PathVariable Long matchId, @PathVariable Long winner, @AuthenticationPrincipal User user) {
        return ResponseEntity.ok()
                .body(matchService.setWinner(matchId, winner, user));
    }

    @GetMapping
    public ResponseEntity<List<MatchDto>> getMatchHistory (@AuthenticationPrincipal User user) {
        return ResponseEntity.ok()
                .body(matchService.getMatchHistory(user));
    }
}
