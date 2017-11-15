package com.anger.service.userservice.controller;

import com.anger.service.userservice.dto.UserDto;
import com.anger.service.userservice.exception.handler.HttpExceptionResponse;
import com.anger.service.userservice.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.apache.commons.httpclient.HttpStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by pjeyamukar on 14/11/2017.
 */
@Api("User Service API")
@RestController
@RequestMapping(value = "/users", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
public class UserController {


    private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    @ApiOperation("Get all data for a given user")
    @GetMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    @ApiResponses(value = {
            @ApiResponse(code = HttpStatus.SC_OK, message = "Success", response = UserDto.class),
            @ApiResponse(code = HttpStatus.SC_BAD_REQUEST, message = "Bad Request", response = HttpExceptionResponse.class),
            @ApiResponse(code = HttpStatus.SC_NOT_FOUND, message = "User Not Found", response = HttpExceptionResponse.class),
            @ApiResponse(code = HttpStatus.SC_INTERNAL_SERVER_ERROR, message = "Internal Server Error", response = HttpExceptionResponse.class)})
    public ResponseEntity<List<UserDto>> getAllUser() {
        LOGGER.info("Fetching all users");

        return ResponseEntity.status(HttpStatus.SC_OK).body(userService.getAllUser());
    }

    @ApiOperation("Get data for a given user")
    @GetMapping(value = "/{userId}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    @ApiResponses(value = {
            @ApiResponse(code = HttpStatus.SC_OK, message = "Success", response = UserDto.class),
            @ApiResponse(code = HttpStatus.SC_BAD_REQUEST, message = "Bad Request", response = HttpExceptionResponse.class),
            @ApiResponse(code = HttpStatus.SC_NOT_FOUND, message = "User Not Found", response = HttpExceptionResponse.class),
            @ApiResponse(code = HttpStatus.SC_INTERNAL_SERVER_ERROR, message = "Internal Server Error", response = HttpExceptionResponse.class)})
    public ResponseEntity<UserDto> getUser(@PathVariable String userId) {

        LOGGER.info("Fetching user with id {}", userId);

        return ResponseEntity.status(HttpStatus.SC_OK).body(userService.getUser(userId));
    }

    @ApiOperation("Create user")
    @PostMapping(value = "/{userId}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    @ApiResponses(value = {
            @ApiResponse(code = HttpStatus.SC_CREATED, message = "User Created", response = UserDto.class),
            @ApiResponse(code = HttpStatus.SC_BAD_REQUEST, message = "Bad Request", response = HttpExceptionResponse.class),
            @ApiResponse(code = HttpStatus.SC_CONFLICT, message = "User Already Exists", response = HttpExceptionResponse.class),
            @ApiResponse(code = HttpStatus.SC_INTERNAL_SERVER_ERROR, message = "Internal Server Error", response = HttpExceptionResponse.class)})
    public ResponseEntity<UserDto> createUser(@PathVariable String userId, @RequestBody @Valid UserDto userDto) {

        LOGGER.info("Received request for creating user with id {}", userId);

        return ResponseEntity.status(HttpStatus.SC_CREATED).body(userService.createUser(userDto));
    }

    @ApiOperation("Update user details")
    @PutMapping(value = "/{userId}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    @ApiResponses(value = {
            @ApiResponse(code = HttpStatus.SC_OK, message = "Success", response = UserDto.class),
            @ApiResponse(code = HttpStatus.SC_BAD_REQUEST, message = "Bad Request", response = HttpExceptionResponse.class),
            @ApiResponse(code = HttpStatus.SC_NOT_FOUND, message = "User Not Found", response = HttpExceptionResponse.class),
            @ApiResponse(code = HttpStatus.SC_INTERNAL_SERVER_ERROR, message = "Internal Server Error", response = HttpExceptionResponse.class)})
    public ResponseEntity<UserDto> updateUser(@PathVariable String userId, @RequestBody UserDto userDto) {

        LOGGER.info("Received request for updating user with id {}", userId);

        return ResponseEntity.status(HttpStatus.SC_OK).body(userService.updateUser(userDto));
    }

    @ApiOperation("Delete user")
    @DeleteMapping(value = "/{userId}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    @ApiResponses(value = {
            @ApiResponse(code = HttpStatus.SC_OK, message = "Success", response = UserDto.class),
            @ApiResponse(code = HttpStatus.SC_BAD_REQUEST, message = "Bad Request", response = HttpExceptionResponse.class),
            @ApiResponse(code = HttpStatus.SC_NOT_FOUND, message = "User Not Found", response = HttpExceptionResponse.class),
            @ApiResponse(code = HttpStatus.SC_INTERNAL_SERVER_ERROR, message = "Internal Server Error",response = HttpExceptionResponse.class)})
    public ResponseEntity<?> deleteUser(@PathVariable String userId) {
        LOGGER.info("Received request for deleting user with id {}", userId);
        userService.deleteUser(userId);
        Map<String, Object> dataMap = new HashMap<>();
        dataMap.put("message", "ID: " + userId + " Deleted successfully");
        return ResponseEntity.status(HttpStatus.SC_OK).body(dataMap);
    }
}
