package com.gocomet.assignment.controllers;


import com.gocomet.assignment.dto.SubmitScoreDTO;
import com.gocomet.assignment.services.ScoreManagerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/leaderboard")
/* Todo: the endpoints for score should be different from what LeaderBoardController has */

@RequiredArgsConstructor
public class ScoreController {

    private final ScoreManagerService scoreManagerService;

    @PostMapping("/submit")
    public ResponseEntity<String> submitScore(@RequestBody SubmitScoreDTO submitScoreDTO) {
        try {
            scoreManagerService.submitScore(submitScoreDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body("Score submitted successfully.");
        } catch (RuntimeException ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error: " + ex.getMessage());
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An unexpected error occurred: " + ex.getMessage());
        }
    }
}
