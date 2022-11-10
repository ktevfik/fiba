package com.example.fibaday2.relation.mvc;

import com.example.fibaday2.relation.entity.Player;
import com.example.fibaday2.relation.entity.Team;
import com.example.fibaday2.relation.repository.TeamRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;

/**
 * @author Tevfik Kadan
 * @created 06/11/2022 - 12:22
 */
@Controller
@RequestMapping("/sports")
public class TeamController {

    private final TeamRepository teamRepository;

    public TeamController(TeamRepository teamRepository) {
        this.teamRepository = teamRepository;
    }

    @GetMapping("/team/insert")
    @ResponseBody
    public String insertTeam() {
        Team team = new Team(0, "Godoro spor");
        team.setPlayerList(new ArrayList<>());
        Player player = new Player(0, "Tevfik Kadan", 54.21);
        player.setTeam(team);
        team.getPlayerList().add(player);

        Player player2 = new Player(0, "Dilek Kadan", 60);
        player2.setTeam(team);
        team.getPlayerList().add(player2);

        Player player3 = new Player(0, "Mehmet Kadan", 40);
        player3.setTeam(team);
        team.getPlayerList().add(player3);

        teamRepository.save(team);

        return "Team save method applied successfully";
    }
}
