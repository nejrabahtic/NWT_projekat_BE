package com.tim3.Match.repositories;


import com.tim3.Match.models.Match;
import org.springframework.data.repository.CrudRepository;


public interface MatchRepository extends CrudRepository<Match, Integer> {
    Iterable<Match> findAllByUserId(Integer id);
    Iterable<Match> findAllByJobId(Integer id);

}
