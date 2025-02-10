package com.gocomet.assignment.controllers;


import com.gocomet.assignment.dto.LeaderBoardDTO;
import com.gocomet.assignment.dto.SubmitScoreDTO;
import com.gocomet.assignment.services.LeaderBoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/leaderboard")
@RequiredArgsConstructor
public class LeaderBoardController {

    private final LeaderBoardService leaderBoardService;

    @GetMapping("/rank/{userId}")
    public Long getTopPlayers(@PathVariable Long userId) {
        return leaderBoardService.getRankForUser(userId);
    }

    @GetMapping("/top")
    public List<LeaderBoardDTO> getTopPlayers() {
        return leaderBoardService.getTopPlayers();
    }

}
