package com.jamdb.japi.entities.content;

import com.jamdb.japi.entities.BaseEntity;
import io.hypersistence.utils.hibernate.type.array.ListArrayType;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@SuppressWarnings("ALL")
@EqualsAndHashCode(callSuper = false)
@Entity(name = "anime")
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString

public class Content extends BaseEntity implements Comparable<Content> {
    @Column(name = "source_id")
    private String sourceId;
    @org.hibernate.annotations.Type(ListArrayType.class)
    @Column(columnDefinition = "text[]")
    private List<String> sources;
    private String title;
    @Enumerated(EnumType.STRING)
    private Type type;
    private Integer episodes;
    @Enumerated(EnumType.STRING)
    private Status status;
    @Embedded
    private Season animeSeason;
    private String picture;
    private String thumbnail;
    @Column(columnDefinition = "TEXT")
    private String description;
    private Double score;
    @org.hibernate.annotations.Type(ListArrayType.class)
    @Column(columnDefinition = "text[]")
    private List<String> synonyms;
    @org.hibernate.annotations.Type(ListArrayType.class)
    @Column(columnDefinition = "text[]")
    private List<String> relations;
    @org.hibernate.annotations.Type(ListArrayType.class)
    @Column(columnDefinition = "text[]")

    private List<String> tags;
    @Column(name = "likes")
    private Integer likes = 0;

    public void setType(String type) {
        this.type = Type.valueOf(type);
    }

    public void setStatus(String status) {
        this.status = Status.valueOf(status);
    }


    @Override
    public int compareTo(Content content) {
        return this.getId().compareTo(content.getId());
    }

}
