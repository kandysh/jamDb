package com.jamdb.japi.dto;

import lombok.*;

import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@ToString
@NoArgsConstructor
public class AddContentDto {
    private String contentId;
}
