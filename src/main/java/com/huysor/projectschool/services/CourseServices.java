package com.huysor.projectschool.services;

import com.huysor.projectschool.entity.Course;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface CourseServices {
    List<Course> findAll();

    Course findById(Long id);

    Course createWithImage(MultipartFile file,Course course);
    Course create(Course course);

    Course update(MultipartFile file, Course course);
    List<Course> findByCategoryId(Long cateId);

    void delete(Long id);
//    String getFileName(String name);

    Course findByName(String name);
}
