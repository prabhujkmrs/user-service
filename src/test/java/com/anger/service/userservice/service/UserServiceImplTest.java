package com.anger.service.userservice.service;


import com.anger.service.userservice.dao.UserDao;
import com.anger.service.userservice.data.DatabaseClient;
import com.anger.service.userservice.dto.UserDto;
import com.anger.service.userservice.exception.ConflictException;
import com.anger.service.userservice.exception.InternalServerErrorException;
import com.anger.service.userservice.exception.NotFoundException;
import com.google.common.collect.ImmutableMap;
import net.bytebuddy.matcher.ElementMatcher;
import org.hamcrest.core.Is;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static net.bytebuddy.matcher.ElementMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.*;

/**
 * Created by gperry on 04/07/2017.
 */
@RunWith(MockitoJUnitRunner.class)
public class UserServiceImplTest {

    private static final String USER_ID = "123456789";

    @Mock
    private DatabaseClient databaseClient;

    @InjectMocks
    private UserService userService = new UserServiceImpl();



    @Test(expected = NotFoundException.class)
    public void whenGetUserThrowsNotFoundExceptionThenRethrowIt() {
        when(databaseClient.getUser(USER_ID)).thenThrow(NotFoundException.class);

        userService.getUser(USER_ID);
    }

    @Test(expected = InternalServerErrorException.class)
    public void whenGetUserThrowsInternalServerErrorExceptionThenRethrowIt() {
        when(databaseClient.getUser(USER_ID)).thenThrow(InternalServerErrorException.class);

        userService.getUser(USER_ID);
    }

    @Test
    public void whenGetUserReturnsValidUserDaoThenReturnUserDto() {

        UserDao userDao = getUserDao();

        when(databaseClient.getUser(USER_ID)).thenReturn(userDao);

        UserDto result = userService.getUser(USER_ID);

        assertThat(result.getUserId(), Is.is(equalTo(userDao.getUserId())));
        assertThat(result.getFirstName(), Is.is(equalTo(userDao.getFirstName())));
        assertThat(result.getLastName(), Is.is(equalTo(userDao.getLastName())));
        assertThat(result.getEmail(), Is.is(equalTo(userDao.getEmail())));
    }

    @Test(expected = ConflictException.class)
    public void whenCreateUserThrowsNotConflictErrorExceptionThenRethrowIt() {
        when(databaseClient.getUser(USER_ID)).thenThrow(ConflictException.class);

        userService.getUser(USER_ID);
    }

    @Test(expected = InternalServerErrorException.class)
    public void whenCreateUserThrowsInternalServerErrorExceptionThenRethrowIt() {
        when(databaseClient.getUser(USER_ID)).thenThrow(InternalServerErrorException.class);

        userService.getUser(USER_ID);
    }

    @Test
    public void whenCreateUserReturnsValidUserDaoThenReturnUserDto() {

        UserDto userDto = UserDto.builder().build();
        UserDao userDao = getUserDao();

        when(databaseClient.createUser(any(UserDto.class))).thenReturn(userDao);

        UserDto result = userService.createUser(userDto);

        assertThat(result.getUserId(), Is.is(equalTo(userDao.getUserId())));
        assertThat(result.getFirstName(), Is.is(equalTo(userDao.getFirstName())));
        assertThat(result.getLastName(), Is.is(equalTo(userDao.getLastName())));
        assertThat(result.getEmail(), Is.is(equalTo(userDao.getEmail())));

    }


    @Test(expected = NotFoundException.class)
    public void whenUpdateUserThrowsNotConflictErrorExceptionThenRethrowIt() {
        when(databaseClient.getUser(USER_ID)).thenThrow(NotFoundException.class);

        userService.getUser(USER_ID);
    }

    @Test(expected = InternalServerErrorException.class)
    public void whenUpdateUserThrowsInternalServerErrorExceptionThenRethrowIt() {
        when(databaseClient.getUser(USER_ID)).thenThrow(InternalServerErrorException.class);

        userService.getUser(USER_ID);
    }
    @Test
    public void shouldUpdateUserDetails() {

        UserDto userDto = UserDto.builder().build();
        UserDao userDao = getUserDao();

        when(databaseClient.updateUser(any(UserDto.class))).thenReturn(userDao);

        UserDto updatedDto = userService.updateUser(userDto);
        verify(databaseClient, times(1)).updateUser(userDto);

        assertEquals(userDao.getUserId(), updatedDto.getUserId());
        assertEquals(userDao.getFirstName(), updatedDto.getFirstName());
        assertEquals(userDao.getLastName(), updatedDto.getLastName());
        assertEquals(userDao.getEmail(), updatedDto.getEmail());

    }

    private UserDao getUserDao() {
        UserDao userDao = new UserDao();
        userDao.setUserId("test");
        userDao.setFirstName("test");
        userDao.setLastName("test");
        userDao.setEmail("test");
        return userDao;
    }
}
