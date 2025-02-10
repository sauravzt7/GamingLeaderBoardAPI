package com.gocomet.assignment.models;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "leaderboard")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class LeaderBoard {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(name = "total_score", nullable = false)
    private int totalScore;
    @Column(name = "rank")
    private int rank;
}
