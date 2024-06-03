package com.huysor.projectschool.dto.category;

import com.huysor.projectschool.dto.course.CourseRequestDTO;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;
@Data
public class CategoryWithCourse {
    private Long id;
    private String code;
    private String name;
    private List<CourseRequestDTO> coursesList;
}
