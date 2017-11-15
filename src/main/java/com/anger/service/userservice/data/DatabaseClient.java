package com.anger.service.userservice.data;

import com.anger.service.userservice.dao.UserDao;
import com.anger.service.userservice.dto.UserDto;

import java.util.List;

/**
 * Created by pjeyamukar on 14/11/2017.
 */
public interface DatabaseClient {

    UserDto getUser(String userId);

    List<UserDto> getAllUsers();

    UserDto createUser(UserDto userDto);

    UserDto updateUser(UserDto userDto);

    void deleteUser(String userId);
}
