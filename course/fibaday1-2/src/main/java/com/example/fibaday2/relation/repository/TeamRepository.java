package com.example.fibaday2.relation.repository;

import com.example.fibaday2.relation.entity.Team;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Tevfik Kadan
 * @created 06/11/2022 - 12:21
 */
@Repository
public interface TeamRepository extends CrudRepository<Team, Long> {
}
