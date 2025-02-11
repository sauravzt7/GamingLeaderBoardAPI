package com.gocomet.assignment.repository;

import com.gocomet.assignment.models.LeaderBoard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
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
    @Modifying
    @Query(value = "UPDATE leaderboard lb SET total_score = subquery.total_score, rank = subquery.rank " +
                   "FROM (SELECT user_id, SUM(score) as total_score, RANK() OVER (ORDER BY SUM(score) DESC) as rank " +
                   "FROM game_sessions GROUP BY user_id) subquery " +
                   "WHERE lb.user_id = subquery.user_id", nativeQuery = true)
    void updateLeaderBoard();


    @Query("SELECT l.rank FROM LeaderBoard l WHERE l.user.id = :userId")
    int findRankByUserId(@Param("userId") Long userId);

}
