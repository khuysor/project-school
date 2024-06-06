package com.huysor.projectschool.dto.course;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
@Data
public class CourseResponse {
    private Long id;
    private String courseName;
    private String teacherName;
    private String description;
    private BigDecimal price;
    private String imageName;
    private String imgUrl;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
}
