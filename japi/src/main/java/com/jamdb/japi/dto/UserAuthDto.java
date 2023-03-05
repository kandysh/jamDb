package com.jamdb.japi.dto;

import lombok.*;

@AllArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class UserAuthDto {
    private String userName;

    private String password;

}
