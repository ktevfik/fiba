package com.example.fibaday2.relation.mvc;

import com.example.fibaday2.relation.entity.Player;
import com.example.fibaday2.relation.repository.PlayerRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @author Tevfik Kadan
 * @created 06/11/2022 - 12:39
 */
@Controller
@RequestMapping("/sports")
public class PlayerController {

    private final PlayerRepository playerRepository;

    public PlayerController(PlayerRepository playerRepository) {
        this.playerRepository = playerRepository;
    }

    @GetMapping("/player/byteam")
    @ResponseBody
    public String findAllByTeamId() {
        List<Player> players = playerRepository.findAllByTeamId(5);

        double totalScore = 0;

        for (Player player : players) {
            totalScore += player.getAverageScore();
            System.out.println("ID: " + player.getPlayerId() + " " + player.getPlayerName() + " " + player.getAverageScore());
        }
        double averageScore = totalScore / players.size();
        return String.format("Player find by team id method applied successfully. Average score:  %f", averageScore);
    }
}
