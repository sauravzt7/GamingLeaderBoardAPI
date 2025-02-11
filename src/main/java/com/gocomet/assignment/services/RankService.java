package com.gocomet.assignment.services;


import com.gocomet.assignment.factory.RankingStrategyFactory;
import com.gocomet.assignment.models.LeaderBoard;
import com.gocomet.assignment.repository.LeaderBoardRepository;
import org.springframework.cache.annotation.Cacheable;
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
        System.out.println("Fetching rank for user from database");
        return leaderboardRepository.findRankByUserId(userId);
    }

}
