package com.tim3.User.repositories;

import com.tim3.User.models.User;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UserRepository extends CrudRepository<User, Integer> {
    Optional<User> findOneByAuthId(Integer AuthId);
}
