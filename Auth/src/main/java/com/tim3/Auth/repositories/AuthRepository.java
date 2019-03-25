package com.tim3.Auth.repositories;

import com.tim3.Auth.models.Auth;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface AuthRepository extends MongoRepository<Auth, ObjectId> {
}
