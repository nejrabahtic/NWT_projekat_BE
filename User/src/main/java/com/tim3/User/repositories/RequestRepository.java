package com.tim3.User.repositories;

import com.tim3.User.models.Request;
import org.springframework.data.repository.CrudRepository;

public interface RequestRepository extends CrudRepository<Request, Integer> {
}
