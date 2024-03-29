package com.jamdb.japi.dto;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
@ToString
@EqualsAndHashCode
@NoArgsConstructor
public class License {
    private String license;
    private String name;
    private String url;
}
