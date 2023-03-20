package com.jamdb.japi.dto;

import com.jamdb.japi.entities.content.Content;
import lombok.*;

import java.util.List;

@Data
@Builder
@ToString
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
public class ApiSeederResponse {
    private License license;

    private String repository;
    private String lastUpdate;
    private List<Content> data;
}
