package com.jamdb.japi.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@ToString
@EqualsAndHashCode
@AllArgsConstructor
public class AnimeDto {
    private String contentId;

    private String status;
    private double score;

    private int episodeProgress;

    private String review;
}
