package com.jamdb.japi.dto;

import com.jamdb.japi.entities.content.Season;
import com.jamdb.japi.entities.content.Type;
import com.jamdb.japi.entities.watchList.Status;
import jakarta.persistence.Embedded;
import lombok.*;

@Data
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class AnimeDetailsDto {
    private String contentId;
    private String title;
    private Type type;

    private String picture;
    private String thumbnail;

    @Embedded
    private Season season;
    private Status status;
    private com.jamdb.japi.entities.content.Status contentStatus;

    private int episodeProgress;
    private int totalEpisodes;
    private double score;
    private String review;
}
