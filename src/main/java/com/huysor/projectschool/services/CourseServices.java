package com.huysor.projectschool.services;

import com.huysor.projectschool.entity.Course;

import java.util.List;

public interface CourseServices {
    List<Course> findAll();

    Course findById(Long id);

    Course create(Course course);

    Course update(Long id, Course course);

    void delete(Long id);
}
