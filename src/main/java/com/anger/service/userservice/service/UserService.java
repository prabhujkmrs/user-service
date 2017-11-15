package com.anger.service.userservice.service;

import com.anger.service.userservice.dto.UserDto;

import java.util.List;

/**
 * Created by pjeyamukar on 14/11/2017.
 */
public interface UserService {

    List<UserDto> getAllUser();

    UserDto getUser(String userId);

    UserDto createUser(UserDto userDto);

    UserDto updateUser(UserDto userDto);

    void deleteUser(String userId);
}
