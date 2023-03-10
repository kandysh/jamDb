package com.jamdb.japi.dto;

import com.jamdb.japi.entities.content.Status;
import com.jamdb.japi.entities.content.Type;
import lombok.*;

@Data
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class ContentDetailsDto {
    private String contentId;
    private String title;
    private String picture;
    private String thumbnail;
    private String description;
    private int totalEpisodes;
    private Type type;
    private Status contentStatus;
    private double score;
}
