package com.gocomet.assignment.services;

import com.gocomet.assignment.models.LeaderBoard;
import com.gocomet.assignment.repository.LeaderBoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;
import org.springframework.cache.annotation.Cacheable;


import java.util.List;

@Service
@RequiredArgsConstructor
public class LeaderBoardService {
    private final LeaderBoardRepository leaderboardRepository;

    public void updateLeaderBoard() {
        leaderboardRepository.updateLeaderBoard();
        evictTopPlayersCache();

    }
    @Cacheable(value = "topPlayers", cacheManager = "cacheManager")
    public List<LeaderBoard> getTopPlayers() {
        return leaderboardRepository.findTop10ByOrderByTotalScoreDesc();
    }

    @CacheEvict(value = "topPlayers", allEntries = true)
    public void evictTopPlayersCache() {
        System.out.println("Evicting cache for topPlayers...");
        // This method can be triggered after updating the leaderboard
    }


}
