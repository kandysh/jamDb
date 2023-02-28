package com.jamdb.japi.dto;

import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class NewUserDto {
    @Email
    private String email;
    private String userName;
    private String password;

}
