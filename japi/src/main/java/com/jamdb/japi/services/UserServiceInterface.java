package com.jamdb.japi.services;

import com.jamdb.japi.dto.NewUserDto;
import com.jamdb.japi.entities.User;
import com.jamdb.japi.exceptions.UserAuthException;

public interface UserServiceInterface {
    public boolean userExists(String userName);
    public User registerNewUser(NewUserDto newUserDto) throws UserAuthException;
}
