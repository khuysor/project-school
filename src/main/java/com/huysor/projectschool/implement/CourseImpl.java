package com.huysor.projectschool.implement;

import com.huysor.projectschool.entity.Course;
import com.huysor.projectschool.exception.ApiRequestException;
import com.huysor.projectschool.mapping.CourseMapper;
import com.huysor.projectschool.repo.CourseRepo;
import com.huysor.projectschool.services.CourseServices;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CourseImpl implements CourseServices {
    private final CourseRepo courseRepo;
    private final CourseMapper courseMapper;

    @Override
    public List<Course> findAll() {
        return courseRepo.findAll(Sort.by(Sort.Direction.DESC, "id"));
    }

    @Override
    public Course findById(Long id) {
        return courseRepo.findById(id).orElseThrow(() -> new ApiRequestException("Course with id : %d not found".valueOf(id)));
    }

    @Override
    public Course create(Course course) {
        LocalDateTime now = LocalDateTime.now();
        course.setCreateTime(now);
        course.setUpdateTime(now);
        return courseRepo.save(course);
    }

    @Override
    public Course update(Long id, Course course) {
        LocalDateTime now = LocalDateTime.now();
         Course findCouse =  findById(id);
        course.setId(findCouse.getId());
        course.setUpdateTime(now);
        return courseRepo.save(course);
    }

    @Override
    public void delete(Long id) {
        Course course =findById(id);
        courseRepo.delete(course);
    }

    @Override
    public List<Course> findByCategoryId(Long cateId) {
        return courseRepo.findCourseByCategoryId(cateId);
    }
}
