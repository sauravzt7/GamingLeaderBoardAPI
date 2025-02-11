package com.gocomet.assignment.controllers;


import com.gocomet.assignment.dto.LeaderBoardDTO;
import com.gocomet.assignment.models.LeaderBoard;
import com.gocomet.assignment.repository.LeaderBoardRepository;
import com.gocomet.assignment.services.LeaderBoardService;
import com.gocomet.assignment.services.RankService;
import com.gocomet.assignment.utility.RateLimiter;
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
    private final LeaderBoardRepository leaderBoardRepository;
    private final RankService rankService;
    private final RateLimiter rateLimiter;

    @GetMapping("/rank/{userId}")
    public ResponseEntity<Long> fetchUserRank(@PathVariable Long userId) {
        if(!rateLimiter.isAllowed(userId)){
            return ResponseEntity.status(HttpStatus.TOO_MANY_REQUESTS).body(-1L);
        }
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

        List<LeaderBoard> topPlayers = leaderBoardRepository.findTop10ByOrderByTotalScoreDesc();

        // Map LeaderBoard entities to DTOs
        List<LeaderBoardDTO> response = topPlayers.stream()
                .map(lb -> new LeaderBoardDTO(lb.getUser().getUserName(), lb.getTotalScore(), lb.getRank()))
                .toList();

        return ResponseEntity.ok(response);
    }



    private Long fetchRankForUser(Long userId) {
        return (long) rankService.getUserRank(userId);
    }

    private List<LeaderBoard> fetchTopPlayersFromLeaderboard() {
        return leaderBoardService.getTopPlayers();
    }
}
