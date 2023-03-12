package com.jamdb.japi.services.AuthService;

import com.jamdb.japi.dto.NewUserDto;
import com.jamdb.japi.dto.UserAuthDto;
import com.jamdb.japi.dto.UserResponse;
import com.jamdb.japi.exceptions.UserAuthException;

public interface AuthServiceInterface {
    boolean userExists(String userName);

    UserResponse registerNewUser(NewUserDto newUserDto) throws UserAuthException;

    UserResponse authenticateUser(UserAuthDto userAuthDto) throws UserAuthException;

}
