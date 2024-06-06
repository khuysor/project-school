package com.huysor.projectschool.dto.category;

import com.huysor.projectschool.dto.course.CourseResponse;
import lombok.Data;

import java.util.List;
@Data
public class CategoryWithCourse {
    private Long id;
    private String code;
    private String name;
    private List<CourseResponse> coursesList;
}
