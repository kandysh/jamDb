package com.jamdb.japi.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString

public class UserResponse {
    private String userName;
    private String email;
    private String token;

}
