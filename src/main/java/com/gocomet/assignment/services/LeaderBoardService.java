package com.gocomet.assignment.services;

import com.gocomet.assignment.dto.LeaderBoardDTO;
import com.gocomet.assignment.dto.SubmitScoreDTO;
import com.gocomet.assignment.models.LeaderBoard;
import com.gocomet.assignment.models.User;
import com.gocomet.assignment.repository.LeaderBoardRepository;
import com.gocomet.assignment.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class LeaderBoardService {
    private final LeaderBoardRepository leaderboardRepository;
    private final UserRepository userRepository;
    private final RankService rankService;


    public void updateLeaderBoard(SubmitScoreDTO submitScoreDTO) {

        User user = userRepository.findById(submitScoreDTO.getUserId()).orElseThrow(() -> new IllegalArgumentException("User not found"));

        LeaderBoard leaderBoard = leaderboardRepository.findById(submitScoreDTO.getUserId())
                .orElse(LeaderBoard.builder().user(user).totalScore(0).rank(0).build());

        leaderBoard.setTotalScore(leaderBoard.getTotalScore() + submitScoreDTO.getScore());
        leaderboardRepository.save(leaderBoard);
        rankService.updateRanks();
    }

    public List<LeaderBoardDTO> getTopPlayers() {
        return leaderboardRepository.findAllOrderedByScore()
                .stream()
                .limit(10)
                .map(l -> new LeaderBoardDTO(l.getUser().getUserName(), l.getTotalScore(), l.getRank()))
                .collect(Collectors.toList());
    }

    public Long getRankForUser(Long userId) {
        LeaderBoard leaderBoard = leaderboardRepository.findById(userId).orElseThrow(() -> new IllegalArgumentException("User not found"));
        return (long) leaderBoard.getRank();
    }


}
