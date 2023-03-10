package com.jamdb.japi.services;

import com.jamdb.japi.dto.NewUserDto;
import com.jamdb.japi.dto.UserAuthDto;
import com.jamdb.japi.dto.UserResponse;
import com.jamdb.japi.entities.User;
import com.jamdb.japi.exceptions.UserAuthException;

import java.util.Optional;

public interface UserServiceInterface {
    public boolean userExists(String userName);
    public UserResponse registerNewUser(NewUserDto newUserDto) throws UserAuthException;
    public UserResponse getUser(String userName) throws UserAuthException;

    public UserResponse authenticateUser(UserAuthDto userAuthDto) throws UserAuthException;

}
