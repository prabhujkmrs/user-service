package com.anger.service.userservice.controller;

import com.anger.service.userservice.data.DatabaseClient;
import com.anger.service.userservice.dto.UserDto;
import com.anger.service.userservice.exception.ConflictException;
import com.anger.service.userservice.exception.InternalServerErrorException;
import com.anger.service.userservice.exception.NotFoundException;
import com.anger.service.userservice.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang.StringUtils;
import org.hamcrest.core.Is;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static net.bytebuddy.matcher.ElementMatchers.is;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class UserControllerTest {

    private static final String USER_ID = "123456789";

    private MockMvc mockMvc;
    private UserDto userDto;

    @Autowired
    private ObjectMapper objectMapper;

    @Mock
    private DatabaseClient databaseClient;

    @Mock
    private UserService userService;


    @InjectMocks
    private UserController controller = new UserController();

    @Before
    public void before() {
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
        userDto = UserDto.builder()
                .userId(USER_ID)
                .firstName("firstname")
                .lastName("lastName")
                .email("email")
                .build();
    }

    /**
     * {@link UserController#getUser(String)}
     */

//    @Test(expected = NotFoundException.class)
//    public void whenGetUserThrowsNotFoundExceptionThenRethrowIt() {
//        doThrow(new NotFoundException()).when(userService).getUser(USER_ID);
//
//        controller.getUser(USER_ID);
//    }
//
//    @Test(expected = InternalServerErrorException.class)
//    public void whenGetUserThrowsInternalServerErrorExceptionThenRethrowIt() {
//        doThrow(new InternalServerErrorException()).when(userService).getUser(USER_ID);
//
//        controller.getUser(USER_ID);
//    }

   /* @Test
    public void whenGetUserExistsThenReturnIt() throws Exception {
        when(userService.getUser(USER_ID)).thenReturn(userDto);

        mockMvc.perform(get("/users/{userId}", USER_ID))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$.userId", Is.is(userDto.getUserId())))
                .andExpect(jsonPath("$.firstName", Is.is(userDto.getFirstName())))
                .andExpect(jsonPath("$.lastName", Is.is(userDto.getLastName())))
                .andExpect(jsonPath("$.email", Is.is(userDto.getEmail())));

        verify(userService, times(1)).getUser(USER_ID);
        verifyNoMoreInteractions(userService);
    }*/

    @Test
    public void whenGetUserThenReturnNotFoundWhenUserIdIsEmpty() throws Exception {

        mockMvc.perform(get("/users/{userId}", StringUtils.EMPTY)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    public void whenGetUserThenReturnNotFoundWhenUserIdIsMissing() throws Exception {

        mockMvc.perform(get("/users/{userId}", "/")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    /**
     * {@link UserController#createUser(String, UserDto)}
     */

//    @Test(expected = ConflictException.class)
//    public void whenCreateUserThrowsConflictErrorExceptionThenRethrowIt() {
//        doThrow(new ConflictException()).when(userService).createUser(userDto);
//
//        controller.createUser(USER_ID, userDto);
//    }
//
//    @Test(expected = InternalServerErrorException.class)
//    public void whenCreateUserThrowsInternalServerErrorExceptionThenRethrowIt() {
//        doThrow(new InternalServerErrorException()).when(userService).createUser(userDto);
//
//        controller.createUser(USER_ID, userDto);
//    }

   /* @Test
    public void whenEmailIsNullThenReturnBadRequest() throws Exception {
        UserDto user = UserDto.builder().userId(USER_ID).build();

        mockMvc.perform(post("/users/{userId}", USER_ID)
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(user)))
            .andExpect(status().isBadRequest());
    }*/

    @Test
    public void shouldCreateUser() throws Exception {

        when(userService.createUser(userDto)).thenReturn(userDto);

        mockMvc.perform(post("/users/{userId}", USER_ID)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(userDto)))
                .andExpect(status().isCreated())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$.userId", Is.is(userDto.getUserId())))
                .andExpect(jsonPath("$.firstName", Is.is(userDto.getFirstName())))
                .andExpect(jsonPath("$.lastName", Is.is(userDto.getLastName())))
                .andExpect(jsonPath("$.email", Is.is(userDto.getEmail())));

        ArgumentCaptor<UserDto> captor = ArgumentCaptor.forClass(UserDto.class);
        verify(userService, times(1)).createUser(captor.capture());
        assertEquals(USER_ID, captor.getValue().getUserId());

        verifyNoMoreInteractions(userService);
    }

    @Test
    public void whenCreateUserThenReturnNotFoundWhenUserIdIsEmpty() throws Exception {

        mockMvc.perform(post("/users/{userId}", StringUtils.EMPTY)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(userDto)))
                .andExpect(status().isNotFound());
    }

    @Test
    public void whenCreateUserThenReturnNotFoundWhenUserIdIsMissing() throws Exception {

        mockMvc.perform(post("/users/{userId}", "/")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(userDto)))
                .andExpect(status().isNotFound());
    }

    @Test
    public void whenCreateUserThenReturnReturnBadRequestWhenPayloadCannotBeBound() throws Exception {

        mockMvc.perform(post("/users/{userId}", USER_ID)
                .contentType(MediaType.APPLICATION_JSON)
                .content("sdgasgasdgasdg"))
                .andExpect(status().isBadRequest());
    }


    /**
     * {@link UserController#updateUser(String, UserDto)}
     */

    @Test(expected = NotFoundException.class)
    public void whenUpdateUserThrowsNotFoundExceptionThenRethrowIt() {
        doThrow(new NotFoundException()).when(userService).updateUser(userDto);

        controller.updateUser(USER_ID, userDto);
    }

    @Test(expected = InternalServerErrorException.class)
    public void whenUpdateUserThrowsInternalServerErrorExceptionThenRethrowIt() {
        doThrow(new InternalServerErrorException()).when(userService).updateUser(userDto);

        controller.updateUser(USER_ID, userDto);
    }

    @Test
    public void shouldUpdateUserDetails() throws Exception {

        when(userService.updateUser(userDto)).thenReturn(userDto);

        mockMvc.perform(put("/users/{userId}", USER_ID)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(userDto)))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$.userId", Is.is(userDto.getUserId())))
                .andExpect(jsonPath("$.firstName", Is.is(userDto.getFirstName())))
                .andExpect(jsonPath("$.lastName", Is.is(userDto.getLastName())))
                .andExpect(jsonPath("$.email", Is.is(userDto.getEmail())));

        ArgumentCaptor<UserDto> captor = ArgumentCaptor.forClass(UserDto.class);
        verify(userService, times(1)).updateUser(captor.capture());
        assertEquals(USER_ID, captor.getValue().getUserId());

        verifyNoMoreInteractions(userService);
    }

    @Test
    public void whenUpdateUserThenReturnNotFoundWhenUserIdIsEmpty() throws Exception {

        mockMvc.perform(put("/users/{userId}", StringUtils.EMPTY)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(userDto)))
                .andExpect(status().isNotFound());
    }

    @Test
    public void whenUpdateUserThenReturnNotFoundWhenUserIdIsMissing() throws Exception {

        mockMvc.perform(put("/users/{userId}", "/")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(userDto)))
                .andExpect(status().isNotFound());
    }

    @Test
    public void whenUpdateUserThenReturnReturnBadRequestWhenPayloadCannotBeBound() throws Exception {

        mockMvc.perform(put("/users/{userId}", USER_ID)
                .contentType(MediaType.APPLICATION_JSON)
                .content("rtweryweryewrywer"))
                .andExpect(status().isBadRequest());
    }
}
