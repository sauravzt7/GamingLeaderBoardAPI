package com.gocomet.assignment.services;


import com.gocomet.assignment.factory.RankingStrategyFactory;
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
        //set the ranking strategy at run time
        RankingStrategyFactory.getNormalRankingStrategy().rank(leaderboardRepository);
    }

    public int getUserRank(Long userId) {
        return leaderboardRepository.findById(userId)
                .map(leaderboard -> leaderboardRepository.findRankByScore(leaderboard.getTotalScore()))
                .orElse(-1);
    }
}
