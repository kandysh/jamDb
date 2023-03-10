package com.jamdb.japi.entities.content;

import com.jamdb.japi.entities.BaseEntity;
import io.hypersistence.utils.hibernate.type.array.ListArrayType;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity(name = "anime")
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)

public class Content extends BaseEntity {
    @org.hibernate.annotations.Type(ListArrayType.class)
    @Column(columnDefinition = "text[]")
    private List<String> sources;
    private String title;
    @Enumerated(EnumType.STRING)
    private ContentType type;
    private int episodes;
    @Enumerated(EnumType.STRING)
    private ContentStatus status;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "anime_season")
    private ContentSeason season;
    private String picture;
    private String thumbnail;
//    @org.hibernate.annotations.Type(ListArrayType.class)
//    private List<String> synonyms;
//    @org.hibernate.annotations.Type(ListArrayType.class)
//    private List<String> relations;
//
//    @org.hibernate.annotations.Type(ListArrayType.class)
//    private List<String> tags;

}
