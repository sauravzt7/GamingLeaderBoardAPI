package com.gocomet.assignment.controllers;


import com.gocomet.assignment.dto.LeaderBoardDTO;
import com.gocomet.assignment.dto.SubmitScoreDTO;
import com.gocomet.assignment.services.LeaderBoardService;
import com.gocomet.assignment.services.RankService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/leaderboard")
@RequiredArgsConstructor
public class LeaderBoardController {

    private final LeaderBoardService leaderBoardService;
    private final RankService rankService;

    @GetMapping("/rank/{userId}")
    public Long getRankForUser(@PathVariable Long userId) {
        return (long) rankService.getUserRank(userId);
    }

    @GetMapping("/top")
    public List<LeaderBoardDTO> getTopPlayers() {
        return leaderBoardService.getTopPlayers();
    }

}
