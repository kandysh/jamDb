package com.jamdb.japi.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@NoArgsConstructor
@Getter
@Setter
public class ApiResponse {
    private LocalDate localDate;
    private String message;
    public ApiResponse(String message){
        this.message=message;
        localDate=LocalDate.now();
    }

}
