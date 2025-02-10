package com.gocomet.assignment.models;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "game_sessions")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder


public class GameSession {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(nullable = false)
    private int score;

    @Column(name = "game_mode", nullable = false)
    private String gameMode;

    @Column(name = "timestamp", nullable = false)
    private LocalDateTime timestamp;


}
