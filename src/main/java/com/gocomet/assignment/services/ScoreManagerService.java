package com.gocomet.assignment.services;

import com.gocomet.assignment.dto.SubmitScoreDTO;
import com.gocomet.assignment.models.GameSession;
import com.gocomet.assignment.models.User;
import com.gocomet.assignment.repository.GameSessionRepository;
import com.gocomet.assignment.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;


@Service
@RequiredArgsConstructor
public class ScoreManagerService {

    private final GameSessionRepository gameSessionRepository;
    private final LeaderBoardService leaderBoardService;
    private final UserRepository userRepository;
    private final RankService rankService;


    @Transactional
    public void submitScore(SubmitScoreDTO dto) {
        User user = userRepository.findById(dto.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found"));

        GameSession session = GameSession.builder()
                .user(user)
                .score(dto.getScore())
                .gameMode(dto.getGameMode())
                .timestamp(LocalDateTime.now())
                .build();
        gameSessionRepository.save(session);
        int totalScore = gameSessionRepository.calculateTotalScore(user.getId());

        leaderBoardService.updateLeaderBoard(dto);
        rankService.updateRanks();
    }

}
