package com.huysor.projectschool.controller;

import com.huysor.projectschool.dto.course.CoursePostDTO;
import com.huysor.projectschool.dto.course.CourseRequestDTO;
import com.huysor.projectschool.entity.Course;
import com.huysor.projectschool.exception.ApiExceptionHandler;
import com.huysor.projectschool.exception.ApiRequestException;
import com.huysor.projectschool.mapping.CourseMapper;
import com.huysor.projectschool.services.CourseServices;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/")
public class CourseController {
    private final CourseServices courseServices;
    private final CourseMapper courseMapper;

    @PostMapping("categories/course")
    public ResponseEntity<?> createCourse(@RequestBody CoursePostDTO courseDTO) {
        Course course = courseMapper.toCourse(courseDTO);
        course = courseServices.create(course);
        return ResponseEntity.ok(course);
    }

    @PutMapping("categories/course/{id}")
    public ResponseEntity<?> updateCourse(@PathVariable("id") Long id, @RequestBody CoursePostDTO courseDTO) {
        Course course = courseMapper.toCourse(courseDTO);
        return ResponseEntity.ok(courseServices.update(id, course));
    }

    @GetMapping("categories/course")
    public ResponseEntity<?> getAllCourse() {
        List<Course> courses = courseServices.findAll();
        List<CourseRequestDTO> courseRequestDTO = courses
                .stream()
                .map(courseMapper::toCourseDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(courseRequestDTO);
    }

    @GetMapping("categories/course/{id}")
    public ResponseEntity<?> getCourseById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(courseServices.findById(id));
    }
    @GetMapping("{id}/course/")
    public ResponseEntity<?> getCourseByCourseId(@PathVariable("id") Long id) {
        List<Course> courses= courseServices.findByCategoryId(id);
        if (courses.isEmpty() ) {
            throw new ApiRequestException("Courses not found for the given category ID.");
        }
        List<CourseRequestDTO> courseRequestDTOS= courses.stream().map(courseMapper::toCourseDTO).collect(Collectors.toList());
        return ResponseEntity.ok(courseRequestDTOS);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> deleteCourse(@PathVariable("id") Long id) {
        courseServices.delete(id);
        String msg = "Cate with id :" + id + " has been deleted";
        HttpStatus status = HttpStatus.NO_CONTENT;
        return new ResponseEntity<>(msg, status);
    }


}
