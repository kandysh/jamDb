package com.jamdb.japi.utils;

import com.jamdb.japi.dto.ContentDetailsDto;
import com.jamdb.japi.entities.content.Content;
import com.jamdb.japi.entities.content.Season;
import com.jamdb.japi.entities.content.Seasons;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

@NoArgsConstructor
@Service
public class UtilFunctions {
    public Function<Content, ContentDetailsDto> contentToDetails = content -> ContentDetailsDto
            .builder()
            .contentId(content.getId().toString())
            .title(content.getTitle())
            .picture(content.getPicture())
            .description(content.getDescription())
            .thumbnail(content.getThumbnail())
            .totalEpisodes(content.getEpisodes())
            .type(content.getType())
            .contentStatus(content.getStatus())
            .score(content.getScore())
            .likes(content.getLikes())
            .season(content.getAnimeSeason())
            .tags(content.getTags())
            .alternativeName(content.getSynonyms().size() == 0 ? null : content.getSynonyms().get(0))
            .sourceId(content.getSourceId())
            .build();
    public Function<List<Content>, List<ContentDetailsDto>> contentToContentDetails = contents -> contents.stream()
            .map(contentToDetails)
            .collect(
                    Collectors.toList());

    public Season getCurrentAnimeSeason() {
        var currDate = LocalDate.now();
        return new Season(Seasons.values()[currDate.getMonthValue() % 3], currDate.getYear());
    }

}
