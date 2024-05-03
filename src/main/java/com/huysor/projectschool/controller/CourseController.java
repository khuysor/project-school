package com.huysor.projectschool.controller;

import com.huysor.projectschool.dto.course.CoursePostDTO;
import com.huysor.projectschool.dto.course.CourseRequestDTO;
import com.huysor.projectschool.entity.Course;
import com.huysor.projectschool.mapping.CourseMapper;
import com.huysor.projectschool.services.CourseServices;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/categories/course")
public class CourseController {
    private final CourseServices courseServices;
    private final CourseMapper courseMapper;

    @PostMapping
    public ResponseEntity<?> createCourse(@RequestBody CoursePostDTO courseDTO) {
        Course course = courseMapper.toCourse(courseDTO);
        course = courseServices.create(course);
        return ResponseEntity.ok(course);
    }

    @PutMapping("{id}")
    public ResponseEntity<?> updateCourse(@PathVariable Long id, @RequestBody CoursePostDTO courseDTO) {
        Course course = courseMapper.toCourse(courseDTO);
        return ResponseEntity.ok(courseServices.update(id, course));
    }

    @GetMapping
    public ResponseEntity<?> getAllCourse() {
        List<Course> courses = courseServices.findAll();
        List<CourseRequestDTO> courseRequestDTO = courses
                .stream()
                .map(courseMapper::toCourseDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(courseRequestDTO);
    }

    @GetMapping("{id}")
    public ResponseEntity<?> getCourseById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(courseServices.findById(id));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> deleteCourse(@PathVariable Long id) {
        courseServices.delete(id);
        String msg = "Cate with id :" + id + " has been deleted";
        HttpStatus status = HttpStatus.NO_CONTENT;
        return new ResponseEntity<>(msg, status);
    }


}
