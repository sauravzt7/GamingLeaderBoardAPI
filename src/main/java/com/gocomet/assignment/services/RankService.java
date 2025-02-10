package com.gocomet.assignment.services;


import com.gocomet.assignment.models.LeaderBoard;
import com.gocomet.assignment.repository.LeaderBoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RankService {

    private final LeaderBoardRepository leaderboardRepository;

    /**
     * Updates ranks for all users based on total score (higher score = better rank).
     */
    @Transactional
    public void updateRanks() {
        List<LeaderBoard> leaderboardList = leaderboardRepository.findAllOrderedByScore();
        int rank = 1;
        for (LeaderBoard l : leaderboardList) {
            l.setRank(rank++);
            leaderboardRepository.save(l);
        }
    }

    /**
     * Returns the rank of a user based on their total score.
     */
    public int getUserRank(Long userId) {
        return leaderboardRepository.findById(userId)
                .map(leaderboard -> leaderboardRepository.findRankByScore(leaderboard.getTotalScore()))
                .orElse(-1);
    }
}
