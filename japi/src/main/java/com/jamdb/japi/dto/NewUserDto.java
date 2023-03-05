package com.jamdb.japi.dto;

import jakarta.validation.constraints.Email;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class NewUserDto {
    @Email
    private String email;
    private String userName;
    private String password;

}
