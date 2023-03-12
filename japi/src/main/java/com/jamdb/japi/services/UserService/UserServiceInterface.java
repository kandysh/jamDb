package com.jamdb.japi.services.UserService;

import com.jamdb.japi.dto.NewUserDto;
import com.jamdb.japi.dto.UserAuthDto;
import com.jamdb.japi.dto.UserResponse;
import com.jamdb.japi.exceptions.UserAuthException;

public interface UserServiceInterface {
    boolean userExists(String userName);

    UserResponse registerNewUser(NewUserDto newUserDto) throws UserAuthException;

    UserResponse getUser(String userName) throws UserAuthException;

    UserResponse authenticateUser(UserAuthDto userAuthDto) throws UserAuthException;
//
//    void addContent(String username, UUID content_id);
//
//    List<Content> showContent(String username) throws UserAuthException;
//
//    void deleteContent(String username, UUID fromString);
}
