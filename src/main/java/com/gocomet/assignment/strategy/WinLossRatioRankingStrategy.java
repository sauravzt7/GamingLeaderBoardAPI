package com.gocomet.assignment.strategy;

import com.gocomet.assignment.repository.LeaderBoardRepository;

public class WinLossRatioRankingStrategy implements RankingStrategy {

    @Override
    public void rank(LeaderBoardRepository leaderBoardRepository) {
        // Assuming LeaderBoard has a getWinLossRatio() method
        //leaderboards.sort((l1, l2) -> Double.compare(l2.getWinLossRatio(), l1.getWinLossRatio()));
        return ;
    }

}
