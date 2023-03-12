package com.jamdb.japi.services.UserService;

import com.jamdb.japi.dto.AddAnimeDto;
import com.jamdb.japi.dto.UserResponse;
import com.jamdb.japi.entities.content.Content;
import com.jamdb.japi.entities.reviews.Review;
import com.jamdb.japi.entities.user.User;
import com.jamdb.japi.entities.watchList.Status;
import com.jamdb.japi.entities.watchList.WatchList;
import com.jamdb.japi.exceptions.UserAuthException;
import com.jamdb.japi.repository.ReviewRepository;
import com.jamdb.japi.repository.UserRepository;
import com.jamdb.japi.repository.WatchListRepository;
import com.jamdb.japi.services.ContentService.ContentServiceInterface;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@Transactional
@AllArgsConstructor
public class UserService implements UserServiceInterface {
    private final UserRepository userRepository;
    private final ContentServiceInterface contentService;
    private final ReviewRepository reviewRepository;
    private final WatchListRepository watchListRepository;

    @Override
    public UserResponse getUser(String userName) throws UserAuthException {
        var user = userRepository.findByUserName(userName).orElseThrow(() -> new UserAuthException(String.format("username %s not Found", userName)));
        return UserResponse.builder().userName(user.getUsername()).email(user.getEmail()).token("").build();
    }


    @Override
    public void addContentToUser(String username, AddAnimeDto addAnimeDto) {
        User user = userRepository.findByUserName(username).orElseThrow();
        Content content = contentService.findContent(UUID.fromString(addAnimeDto.getAnimeId())).orElseThrow();
        Review review = reviewRepository.save(
                Review
                        .builder()
                        .review(addAnimeDto.getReview().getBytes())
                        .user(user)
                        .content(content)
                        .build()
        );
        WatchList newAnime = WatchList
                .builder()
                .user(user)
                .content(content)
                .review(review)
                .status(Status.valueOf(addAnimeDto.getStatus().toUpperCase()))
                .score(addAnimeDto.getScore())
                .episodeProgress(addAnimeDto.getEpisodeProgress())
                .totalEpisodes(content.getEpisodes())
                .build();
        watchListRepository.save(newAnime);
    }

}
