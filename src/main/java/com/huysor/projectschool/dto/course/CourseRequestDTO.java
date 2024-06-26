package com.huysor.projectschool.dto.course;

import com.huysor.projectschool.dto.category.CategoryRequestDTO;
import com.huysor.projectschool.entity.Category;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
@Data
public class CourseRequestDTO {
    private Long id;
    private String courseName;
    private String teacherName;
    private String description;
    private BigDecimal price;
    private String imageName;
    private String imgUrl;
    private CategoryRequestDTO category;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;

}
