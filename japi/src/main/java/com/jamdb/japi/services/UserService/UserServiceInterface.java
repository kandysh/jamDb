package com.jamdb.japi.services.UserService;

import com.jamdb.japi.dto.NewUserDto;
import com.jamdb.japi.dto.UserAuthDto;
import com.jamdb.japi.dto.UserResponse;
import com.jamdb.japi.entities.content.Content;
import com.jamdb.japi.entities.user.User;
import com.jamdb.japi.exceptions.UserAuthException;

import java.util.Set;
import java.util.UUID;

public interface UserServiceInterface {
    public boolean userExists(String userName);
    public UserResponse registerNewUser(NewUserDto newUserDto) throws UserAuthException;
    public UserResponse getUser(String userName) throws UserAuthException;

    public UserResponse authenticateUser(UserAuthDto userAuthDto) throws UserAuthException;
    public void addContent(String username, UUID content_id);
    public Set<Content> showContent(String username) throws UserAuthException;

}
