package com.tim3.Match.repositories;


import com.tim3.Match.models.Match;
import org.springframework.data.repository.CrudRepository;
import java.util.List;

public interface MatchRepository extends CrudRepository<Match, Integer> {
    Iterable<Match> findAllByUserId(Integer id);
    Iterable<Match> findAllByJobId(Integer id);
    List<Match> findByCompanyId(Integer companyId);

}
