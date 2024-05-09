package com.huysor.projectschool.implement;

import com.huysor.projectschool.entity.Course;
import com.huysor.projectschool.exception.ApiRequestException;
import com.huysor.projectschool.repo.CourseRepo;
import com.huysor.projectschool.services.CourseServices;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CourseImpl implements CourseServices {
    private final CourseRepo courseRepo;

    @Override
    public List<Course> findAll() {
        return courseRepo.findAll();
    }

    @Override
    public Course findById(Long id) {
        return courseRepo.findById(id).orElseThrow(() -> new ApiRequestException("Course with id : %d not found".valueOf(id)));
    }

    @Override
    public Course create(Course course) {
        return courseRepo.save(course);
    }

    @Override
    public Course update(Long id, Course course) {
        findById(id);
        course.setId(id);
        return courseRepo.save(course);
    }

    @Override
    public void delete(Long id) {
        Course course =findById(id);
        courseRepo.delete(course);
    }

}
