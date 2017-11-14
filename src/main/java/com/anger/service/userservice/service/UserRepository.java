package com.anger.service.userservice.service;

import com.anger.service.userservice.domain.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User, String> {

    public User findByLogin(String login);

}