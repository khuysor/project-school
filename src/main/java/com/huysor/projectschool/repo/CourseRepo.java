package com.huysor.projectschool.repo;

import com.huysor.projectschool.dto.category.CategoryRequestDTO;
import com.huysor.projectschool.entity.Category;
import com.huysor.projectschool.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRepo extends JpaRepository<Course,Long> {
    Course findCourseCategoryById(Long id);
}
