package com.gocomet.assignment.controllers;


import com.gocomet.assignment.dto.SubmitScoreDTO;
import com.gocomet.assignment.services.ScoreManagerService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/leaderboard")
@RequiredArgsConstructor
public class ScoreController {

    private final ScoreManagerService ScoreManagerService;

    @PostMapping("/submit")
    public void submitScore(@RequestBody SubmitScoreDTO submitScoreDTO) {
        ScoreManagerService.submitScore(submitScoreDTO);
    }

}
