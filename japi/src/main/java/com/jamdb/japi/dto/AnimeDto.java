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
    private Double score;

    private Integer episodeProgress;

    private String review;
}
