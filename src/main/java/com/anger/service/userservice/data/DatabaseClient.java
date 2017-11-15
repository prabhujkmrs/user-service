package com.anger.service.userservice.data;

import com.anger.service.userservice.dao.UserDao;
import com.anger.service.userservice.dto.UserDto;

import java.util.List;

/**
 * Created by pjeyamukar on 14/11/2017.
 */
public interface DatabaseClient {

    UserDao getUser(String userId);

    UserDao createUser(UserDto userDto);

    UserDao updateUser(UserDto userDto);

    List<UserDao> getAllUsers();

    void deleteUser(String userId);
}
