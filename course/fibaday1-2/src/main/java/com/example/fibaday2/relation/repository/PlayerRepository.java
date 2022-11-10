package com.example.fibaday2.relation.repository;

import com.example.fibaday2.relation.entity.Player;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * @author Tevfik Kadan
 * @created 06/11/2022 - 12:20
 */
public interface PlayerRepository extends CrudRepository<Player, Long> {

    @Query("SELECT p from Player p where p.team.teamId = :teamId")
    List<Player> findAllByTeamId(long teamId);
}
