package com.gocomet.assignment.factory;

import com.gocomet.assignment.repository.LeaderBoardRepository;
import com.gocomet.assignment.strategy.NormalRankingStrategy;
import com.gocomet.assignment.strategy.RankingStrategy;

public class RankingStrategyFactory {

    private LeaderBoardRepository leaderBoardRepository;

    public static RankingStrategy getNormalRankingStrategy(){
        return new NormalRankingStrategy();
    }
}
