package com.gocomet.assignment.strategy;

import com.gocomet.assignment.models.LeaderBoard;
import com.gocomet.assignment.repository.LeaderBoardRepository;

import java.util.List;

public interface RankingStrategy {
    void rank(LeaderBoardRepository leaderBoardRepository);
}
