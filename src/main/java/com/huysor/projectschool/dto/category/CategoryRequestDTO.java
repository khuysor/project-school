package com.huysor.projectschool.dto.category;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class CategoryRequestDTO {
    private Long id;
    private String code;
    private String name;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;


}
