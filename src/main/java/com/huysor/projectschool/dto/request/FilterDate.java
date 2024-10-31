package com.huysor.projectschool.dto.request;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class FilterDate {
    private LocalDateTime startDate;
    private LocalDateTime endDate;
}
