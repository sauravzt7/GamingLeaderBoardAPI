package com.gocomet.assignment.repository;

import com.gocomet.assignment.models.GameSession;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface GameSessionRepository extends JpaRepository<GameSession, Long> {


    @Query("SELECT COALESCE(SUM(gs.score), 0) FROM GameSession gs WHERE gs.user.id = :userId")
    int calculateTotalScore(@Param("userId") Long userId);
}
