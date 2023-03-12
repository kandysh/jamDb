package com.jamdb.japi.services.UserService;

import com.jamdb.japi.dto.AnimeDetailsDto;
import com.jamdb.japi.dto.AnimeDto;
import com.jamdb.japi.dto.UserResponse;
import com.jamdb.japi.entities.content.Content;
import com.jamdb.japi.entities.reviews.Review;
import com.jamdb.japi.entities.user.User;
import com.jamdb.japi.entities.watchList.Status;
import com.jamdb.japi.entities.watchList.WatchList;
import com.jamdb.japi.entities.watchList.WatchListId;
import com.jamdb.japi.exceptions.UserAuthException;
import com.jamdb.japi.repository.ReviewRepository;
import com.jamdb.japi.repository.UserRepository;
import com.jamdb.japi.repository.WatchListRepository;
import com.jamdb.japi.services.ContentService.ContentServiceInterface;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

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
    public void addContentToUser(String username, AnimeDto animeDto) {
        User user = userRepository.findByUserName(username).orElseThrow();
        Content content = contentService.findContent(UUID.fromString(animeDto.getContentId())).orElseThrow();
        Review review = reviewRepository.save(
                Review
                        .builder()
                        .review(animeDto.getReview())
                        .user(user)
                        .content(content)
                        .build()
        );
        WatchList newAnime = WatchList
                .builder()
                .id(
                        WatchListId
                                .builder()
                                .user(user)
                                .content(content)
                                .build()
                )
                .review(review)
                .status(Status.valueOf(animeDto.getStatus().toUpperCase()))
                .score(animeDto.getScore())
                .episodeProgress(animeDto.getEpisodeProgress())
                .totalEpisodes(content.getEpisodes())
                .build();
        watchListRepository.save(newAnime);
    }

    @Override
    public void editContentofUser(String username, AnimeDto editAnimeDto) {
        User user = userRepository.findByUserName(username).orElseThrow();
        Content content = contentService.findContent(UUID.fromString(editAnimeDto.getContentId())).orElseThrow();
        Review review = reviewRepository.getReviewByUserAndContent(user, content);
        review.setReview(editAnimeDto.getReview());
        reviewRepository.save(review);
        WatchList anime = watchListRepository.findById(new WatchListId(user, content)).orElseThrow();
        anime.setStatus(Status.valueOf(editAnimeDto.getStatus().toUpperCase()));
        anime.setScore(editAnimeDto.getScore());
        anime.setEpisodeProgress(editAnimeDto.getEpisodeProgress());
        watchListRepository.save(anime);
    }

    @Override
    public void deleteContentofUser(String username, String contentId) {
        User user = userRepository.findByUserName(username).orElseThrow();
        Content content = contentService.findContent(UUID.fromString(contentId)).orElseThrow();
        Review review = reviewRepository.getReviewByUserAndContent(user, content);
        reviewRepository.deleteById(review.getId());
        watchListRepository.deleteById(WatchListId.builder().user(user).content(content).build());
    }

    @Override
    public List<AnimeDetailsDto> showAnimesOfUser(String username) {
        User user = userRepository.findByUserName(username).orElseThrow();
        var watchList = watchListRepository.findWatchListById_UserId(user.getId());
        return watchList.stream().map(
                anime -> {
                    var content = contentService.findContent(anime.getId().getContent().getId()).orElseThrow();
                    var review = reviewRepository.getReviewByUserAndContent(user, content);
                    return AnimeDetailsDto
                            .builder()
                            .contentId(content.getId().toString())
                            .title(content.getTitle())
                            .type(content.getType())
                            .picture(content.getPicture())
                            .thumbnail(content.getThumbnail())
                            .season(content.getAnimeSeason())
                            .status(anime.getStatus())
                            .contentStatus(content.getStatus())
                            .episodeProgress(anime.getEpisodeProgress())
                            .totalEpisodes(content.getEpisodes())
                            .score(anime.getScore())
                            .review(review.getReview())
                            .build();
                }).collect(Collectors.toList());
    }

}
