package com.huysor.projectschool.dto.course;


import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
@Data
public class CoursePostDTO {
    private String courseName;
    private String teacherName;
    private String description;
    private BigDecimal price;
    private Integer cateId;
}
