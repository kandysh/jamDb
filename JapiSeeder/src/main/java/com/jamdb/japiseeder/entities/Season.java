package com.jamdb.japiseeder.entities;

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
    private Integer year;

    public void setSeason(String season) {
        this.season = Seasons.valueOf(season);
    }


}
