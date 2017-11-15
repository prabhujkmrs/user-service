package com.anger.service.userservice.service;


import com.anger.service.userservice.dao.UserDao;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<UserDao, String> {

    public UserDao findByLogin(String login);

    public UserDao findByUserId(String userId);

}