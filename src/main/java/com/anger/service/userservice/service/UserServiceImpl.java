package com.anger.service.userservice.service;

import com.anger.service.userservice.dao.UserDao;
import com.anger.service.userservice.data.DatabaseClient;
import com.anger.service.userservice.dto.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by pjeyamukar on 14/11/2017.
 */
@Service
public class UserServiceImpl implements UserService {


    @Autowired
    private DatabaseClient databaseClient;

    @Override
    public UserDto getUser(String userId) {

        UserDao userDao = databaseClient.getUser(userId);

        return buildDto(userDao);
    }

    @Override
    public UserDto createUser(UserDto userDto) {

        UserDao userDao = databaseClient.createUser(userDto);

        return buildDto(userDao);
    }

    @Override
    public UserDto updateUser(UserDto userDto) {

        UserDao userDao = databaseClient.updateUser(userDto);

        return buildDto(userDao);
    }

    private UserDto buildDto(UserDao userDao) {
        return UserDto.builder()
                .userId(userDao.getUserId())
                .firstName(userDao.getFirstName())
                .lastName(userDao.getLastName())
                .email(userDao.getEmail())
                .build();
    }
}
