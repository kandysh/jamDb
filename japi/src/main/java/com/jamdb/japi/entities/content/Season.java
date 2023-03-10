package com.jamdb.japi.entities.content;

import com.jamdb.japi.entities.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

@Embeddable
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Season{
    @Enumerated(EnumType.STRING)
    private Seasons season;
    @Column(nullable = true)
    private int year;
    public void setSeason(String season){
        this.season = Seasons.valueOf(season);
    }


}
