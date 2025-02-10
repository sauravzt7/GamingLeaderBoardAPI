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
    @Transactional
    public void updateRanks() {
        List<LeaderBoard> leaderboardList = leaderboardRepository.findAllOrderedByScore();
        int rank = 1;
        for (LeaderBoard l : leaderboardList) {
            l.setRank(rank++);
            leaderboardRepository.save(l);
        }
    }

    public int getUserRank(Long userId) {
        return leaderboardRepository.findById(userId)
                .map(leaderboard -> leaderboardRepository.findRankByScore(leaderboard.getTotalScore()))
                .orElse(-1);
    }
}
