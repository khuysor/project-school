package com.huysor.projectschool.dto;


import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
@Data
public class CourseDTO {
    private Long id=null;
    private String courseName;
    private String teacherName;
    private String description;
    private BigDecimal price;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
    private Integer cateId;
}
