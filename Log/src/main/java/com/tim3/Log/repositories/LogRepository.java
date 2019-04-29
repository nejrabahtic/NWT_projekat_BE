package com.tim3.Log.repositories;

import com.tim3.Log.models.Log;
import org.springframework.data.repository.CrudRepository;

public interface LogRepository extends CrudRepository<Log, Integer> {
}
