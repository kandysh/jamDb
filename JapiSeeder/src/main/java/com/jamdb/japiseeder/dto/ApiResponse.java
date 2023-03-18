package com.jamdb.japiseeder.dto;

import com.jamdb.japiseeder.entities.Content;
import lombok.*;

import java.util.List;

@Data
@Builder
@ToString
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
public class ApiResponse {
    private License license;

    private String repository;
    private String lastUpdate;
    private List<Content> data;

}
