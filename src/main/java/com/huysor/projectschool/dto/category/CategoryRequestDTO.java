package com.huysor.projectschool.dto.category;

import com.huysor.projectschool.dto.course.CourseRequestDTO;
import com.huysor.projectschool.entity.Course;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data
public class CategoryRequestDTO {
    private Long id;
    private String code;
    private String name;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;


}
