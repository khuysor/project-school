package com.huysor.projectschool.dto.register;

import com.huysor.projectschool.entity.Course;
import lombok.Data;


import java.math.BigDecimal;
import java.util.List;

@Data
public class RegisterCreateDTO {
    private Long stuId;
    private List<Course> courses;
    private BigDecimal price;
}
