package com.gocomet.assignment.strategy;

import com.gocomet.assignment.models.LeaderBoard;
import com.gocomet.assignment.repository.LeaderBoardRepository;
import lombok.AllArgsConstructor;

import java.util.List;

@AllArgsConstructor
public class NormalRankingStrategy implements RankingStrategy {

    @Override
    public void rank(LeaderBoardRepository leaderboardRepository) {
        List<LeaderBoard> leaderboardList = leaderboardRepository.findAllOrderedByScore();
        int rank = 1;
        for (LeaderBoard l : leaderboardList) {
            l.setRank(rank++);
            leaderboardRepository.save(l);
        }
    }
}
