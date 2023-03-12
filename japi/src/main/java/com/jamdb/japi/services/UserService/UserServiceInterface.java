package com.jamdb.japi.services.UserService;

import com.jamdb.japi.dto.AddAnimeDto;
import com.jamdb.japi.dto.UserResponse;
import com.jamdb.japi.exceptions.UserAuthException;

public interface UserServiceInterface {

    UserResponse getUser(String userName) throws UserAuthException;

    void addContentToUser(String username, AddAnimeDto addAnimeDto);
}
