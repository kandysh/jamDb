package com.jamdb.japi.dto;

import lombok.*;

import java.util.List;

@Data
@Builder
@ToString
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
public class DeadEntries {
    List<String> deadEntries;
}
