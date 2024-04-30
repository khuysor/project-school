package com.example.huysor.projectschool.dto;

import lombok.Data;

import java.time.LocalDateTime;
@Data
public class CategoryDTO {
    private Long id;
    private String code;
    private String name;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
}
