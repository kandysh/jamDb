package com.jamdb.japi.entities.watchList;

import com.jamdb.japi.entities.reviews.Review;
import jakarta.persistence.*;
import lombok.*;

@Entity(name = "watch_list")
@Data
@Builder
@ToString
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
@NoArgsConstructor
public class WatchList {
    @EmbeddedId
    private WatchListId id;
    @Enumerated(EnumType.STRING)
    private Status status;

    private Double score;

    @Column(name = "episode_progress")
    private Integer episodeProgress;

    @Column(name = "total_episodes")
    private Integer totalEpisodes;

    @OneToOne
    @JoinColumn(name = "review")
    private Review review;
}
