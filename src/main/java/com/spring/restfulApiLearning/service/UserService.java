package com.spring.restfulApiLearning.service;

import com.spring.restfulApiLearning.dto.UserDto;
import com.spring.restfulApiLearning.entity.User;

import java.util.List;

public interface UserService {

    UserDto createUser(UserDto user);
    UserDto getUserById(Long id);
    List<UserDto> getAllUsers();
    UserDto updateUser(UserDto user);
    void deleteUser (Long id);

}
