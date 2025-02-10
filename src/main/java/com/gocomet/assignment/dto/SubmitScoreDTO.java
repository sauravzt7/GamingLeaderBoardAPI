package com.gocomet.assignment.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class SubmitScoreDTO {
    private Long userId;
    private Integer score;
    private String gameMode;
}
