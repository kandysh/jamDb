package com.jamdb.japi.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class NewUserDto {
    private String email;
    private String userName;
    private String password;

}
