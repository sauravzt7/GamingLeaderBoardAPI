package com.gocomet.assignment.controllers;


import com.gocomet.assignment.dto.LeaderBoardDTO;
import com.gocomet.assignment.dto.SubmitScoreDTO;
import com.gocomet.assignment.services.LeaderBoardService;
import com.gocomet.assignment.services.RankService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/leaderboard")
@RequiredArgsConstructor
public class LeaderBoardController {

    private final LeaderBoardService leaderBoardService;
    private final RankService rankService;

    @GetMapping("/rank/{userId}")
    public ResponseEntity<Long> fetchUserRank(@PathVariable Long userId) {
        try {
            Long rank = fetchRankForUser(userId);
            if (rank == -1) return ResponseEntity.status(HttpStatus.NOT_FOUND).body(rank);
            return ResponseEntity.ok(rank);
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(-1L);
        }
    }

    @GetMapping("/top")
    public ResponseEntity<List<LeaderBoardDTO>> fetchTopPlayers() {
        try {
            List<LeaderBoardDTO> topPlayers = fetchTopPlayersFromLeaderboard();
            return ResponseEntity.ok(topPlayers);
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }


    private Long fetchRankForUser(Long userId) {
        return (long) rankService.getUserRank(userId);
    }

    private List<LeaderBoardDTO> fetchTopPlayersFromLeaderboard() {
        return leaderBoardService.getTopPlayers();
    }
}
