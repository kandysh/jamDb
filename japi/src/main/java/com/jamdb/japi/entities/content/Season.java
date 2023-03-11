package com.jamdb.japi.entities.content;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Embeddable
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Season {
    @Enumerated(EnumType.STRING)
    private Seasons season;
    @Column(nullable = true)
    private int year;

    public void setSeason(String season) {
        this.season = Seasons.valueOf(season);
    }


}
