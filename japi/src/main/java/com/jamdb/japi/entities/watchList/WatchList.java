package com.jamdb.japi.entities.watchList;

import com.jamdb.japi.entities.BaseEntity;
import com.jamdb.japi.entities.content.Content;
import com.jamdb.japi.entities.reviews.Review;
import com.jamdb.japi.entities.user.User;
import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.*;

@Entity(name = "watch_list")
@Data
@Builder
@ToString
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
@NoArgsConstructor
public class WatchList extends BaseEntity {
    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;
    @OneToOne
    @JoinColumn(name = "anime_id")
    private Content content;

    @Enumerated(EnumType.STRING)
    private Status status;
    @Min(0)
    @Max(10)
    private int score;

    @Column(name = "episode_progress")
    private int episodeProgress;

    @Column(name = "total_episodes")
    private int totalEpisodes;

    @OneToOne
    @JoinColumn(name = "review")
    private Review review;
}
