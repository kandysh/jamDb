package com.jamdb.japi.dto;

import lombok.*;

import java.time.LocalDate;

@NoArgsConstructor
@Data
@ToString
@EqualsAndHashCode
@AllArgsConstructor
public class ApiResponse {
    private LocalDate localDate;
    private String message;

    public ApiResponse(String message) {
        this.message = message;
        localDate = LocalDate.now();
    }

}
