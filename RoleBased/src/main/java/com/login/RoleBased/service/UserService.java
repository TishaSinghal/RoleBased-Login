package com.login.RoleBased.service;

import java.util.List;

import com.login.RoleBased.model.User;
import com.login.RoleBased.dto.UserDto;


public interface UserService {
    void saveUser(UserDto userDto);

    User findUserByEmail(String email);

    List<UserDto> findAllUsers();
}