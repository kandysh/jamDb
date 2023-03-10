package com.jamdb.japi.entities.content;

import com.jamdb.japi.entities.BaseEntity;
import com.jamdb.japi.entities.user.User;
import io.hypersistence.utils.hibernate.type.array.ListArrayType;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

@EqualsAndHashCode(callSuper = false)
@Entity(name = "anime")
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor

public class Content extends BaseEntity{
    @org.hibernate.annotations.Type(ListArrayType.class)
    @Column(columnDefinition = "text[]")
    private List<String> sources;
    private String title;
    @Enumerated(EnumType.STRING)
    private Type type;
    private int episodes;
    @Enumerated(EnumType.STRING)
    private Status status;
    @Embedded
    private Season animeSeason;
    private String picture;
    private String thumbnail;
    @org.hibernate.annotations.Type(ListArrayType.class)
    @Column(columnDefinition = "text[]")
    private List<String> synonyms;
    @org.hibernate.annotations.Type(ListArrayType.class)
    @Column(columnDefinition = "text[]")
    private List<String> relations;
    @org.hibernate.annotations.Type(ListArrayType.class)
    @Column(columnDefinition = "text[]")

    private List<String> tags;

    public void setType(String type){
        this.type = Type.valueOf(type);
    }
    public void setStatus(String status){
        this.status = Status.valueOf(status);
    }


}
