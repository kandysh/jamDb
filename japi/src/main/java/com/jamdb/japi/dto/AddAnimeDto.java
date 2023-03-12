package com.jamdb.japi.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@ToString
@EqualsAndHashCode
@AllArgsConstructor
public class AddAnimeDto {
    private String animeId;

    private String status;
    private int score;

    private int episodeProgress;

    private String review;
}
