package com.gocomet.assignment.services;

import com.gocomet.assignment.dto.LeaderBoardDTO;
import com.gocomet.assignment.models.LeaderBoard;
import com.gocomet.assignment.models.User;
import com.gocomet.assignment.repository.LeaderBoardRepository;
import com.gocomet.assignment.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class LeaderBoardService {
    private final LeaderBoardRepository leaderboardRepository;

    public void updateLeaderBoard(User user, int score) {
        LeaderBoard leaderBoard = leaderboardRepository.findByUserId(user.getId());

        if(leaderBoard != null){
            leaderBoard.setTotalScore(leaderBoard.getTotalScore() + score);
        }
        else {
            leaderBoard = LeaderBoard.builder()
                    .user(user)
                    .totalScore(score)
                    .build();
        }
        leaderboardRepository.save(leaderBoard);
    }

    public List<LeaderBoardDTO> getTopPlayers() {
        return leaderboardRepository.findAllOrderedByScore()
                .stream()
                .limit(10)
                .map(l -> new LeaderBoardDTO(l.getUser().getUserName(), l.getTotalScore(), l.getRank()))
                .collect(Collectors.toList());
    }


}
