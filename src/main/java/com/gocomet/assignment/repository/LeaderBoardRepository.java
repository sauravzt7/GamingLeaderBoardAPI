package com.gocomet.assignment.repository;

import com.gocomet.assignment.models.LeaderBoard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface LeaderBoardRepository extends JpaRepository<LeaderBoard, Long> {
    List<LeaderBoard> findTop10ByOrderByTotalScoreDesc();

    @Query("SELECT l FROM LeaderBoard l ORDER BY l.totalScore DESC")
    List<LeaderBoard> findAllOrderedByScore();

    @Query("SELECT COUNT(l) + 1 FROM LeaderBoard l WHERE l.totalScore > :score")
    int findRankByScore(@Param("score") int score);

    LeaderBoard findByUserId(Long id);
}
