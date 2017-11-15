package com.anger.service.userservice.service;

import com.anger.service.userservice.dao.UserDao;
import com.anger.service.userservice.data.DatabaseClient;
import com.anger.service.userservice.dto.UserDto;
import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by pjeyamukar on 14/11/2017.
 */
@Service
public class UserServiceImpl implements UserService {


    @Autowired
    private DatabaseClient databaseClient;


    @Autowired
    DozerBeanMapper mapper;

    @Override
    public List<UserDto> getAllUser() {

        List<UserDto> userDtoList = new ArrayList<>();
        List<UserDao> userDaoList = databaseClient.getAllUsers();

        for(UserDao userDao : userDaoList) {
            userDtoList.add(mapDaoToDtoObject(userDao));
        }

        return userDtoList;
    }

    @Override
    public UserDto getUser(String userId) {

        UserDao userDao = databaseClient.getUser(userId);

        return mapDaoToDtoObject(userDao);
    }

    @Override
    public UserDto createUser(UserDto userDto) {
        //UserDao userDaoCopy = mapDtoToDaoObject(userDto);
        UserDao userDao = databaseClient.createUser(userDto);

        return mapDaoToDtoObject(userDao);
    }

    @Override
    public UserDto updateUser(UserDto userDto) {
        //UserDao userDaoCopy = mapDtoToDaoObject(userDto);
        UserDao userDao = databaseClient.updateUser(userDto);

        return mapDaoToDtoObject(userDao);
    }

    @Override
    public void deleteUser(String userId) {

        databaseClient.deleteUser(userId);
    }

    private UserDto buildDto(UserDao userDao) {
        return UserDto.builder()
                .userId(userDao.getUserId())
                .firstName(userDao.getFirstName())
                .lastName(userDao.getLastName())
                .email(userDao.getEmail())
                .build();
    }

    private UserDto mapDaoToDtoObject(UserDao userDao) {
        return mapper.map(userDao, UserDto.class);
    }

    private UserDao mapDtoToDaoObject(UserDto userDto) {
        return mapper.map(userDto, UserDao.class);
    }
}
