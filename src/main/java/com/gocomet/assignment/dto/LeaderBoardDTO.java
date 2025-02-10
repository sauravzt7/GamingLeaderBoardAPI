package com.gocomet.assignment.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LeaderBoardDTO {
    private String userName;
    private int totalScore;
    private int rank;
}
