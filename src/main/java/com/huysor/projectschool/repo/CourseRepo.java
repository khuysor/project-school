package com.huysor.projectschool.repo;

import com.huysor.projectschool.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRepo extends JpaRepository<Course,Long> {
}
