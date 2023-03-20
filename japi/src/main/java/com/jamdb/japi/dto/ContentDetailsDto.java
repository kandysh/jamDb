package com.jamdb.japi.dto;

import com.jamdb.japi.entities.content.Status;
import com.jamdb.japi.entities.content.Type;
import lombok.*;

import java.util.List;

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
    private Integer totalEpisodes;
    private Type type;
    private Status contentStatus;
    private Double score;
    private Integer likes;
    private String season;
    private Integer year;
    private String alternativeName;
    private List<String> tags;
    private String sourceId;
}
