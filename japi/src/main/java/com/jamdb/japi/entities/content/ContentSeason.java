package com.jamdb.japi.entities.content;

import com.jamdb.japi.entities.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

@Entity(name = "anime_season")
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode(callSuper = false)
public class ContentSeason extends BaseEntity {
    @Enumerated(EnumType.STRING)
    private ContentSeasons season;
    @Column(nullable = true)
    private int year;


}
