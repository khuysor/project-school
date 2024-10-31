package com.huysor.projectschool.repo;


import com.huysor.projectschool.entity.Course;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface CourseRepo extends JpaRepository<Course, Long> {
    List<Course> findCourseByCategoryId(Long id);

    Course findByImageName(String name);
    Page<Course> findByCreateTimeAfterAndUpdateTimeBefore(
            LocalDateTime createStartDate,
            LocalDateTime updateEndDate,
            Pageable pageable
    );
}
