package com.gocomet.assignment.models;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name= "username", unique = true, nullable = false)
    private String userName;

    @Column(name= "join_date", nullable = false)
    private LocalDateTime joinDate;
}
