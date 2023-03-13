package com.jamdb.japi.dto;

import lombok.*;

@Data
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class WelcomeApiDto {
    private String message;
    private String url;
    private String user;
    private String content;
    private String auth;
}
