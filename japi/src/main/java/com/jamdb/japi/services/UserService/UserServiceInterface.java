package com.jamdb.japi.services.UserService;

import com.jamdb.japi.dto.AnimeDetailsDto;
import com.jamdb.japi.dto.AnimeDto;
import com.jamdb.japi.dto.UserResponse;
import com.jamdb.japi.exceptions.UserAuthException;

import java.util.List;

public interface UserServiceInterface {

    UserResponse getUser(String userName) throws UserAuthException;

    void addContentToUser(String username, AnimeDto animeDto);

    void editContentofUser(String username, AnimeDto editAnimeDto);

    void deleteContentofUser(String username, String contentId);

    List<AnimeDetailsDto> showAnimesOfUser(String username);
}
