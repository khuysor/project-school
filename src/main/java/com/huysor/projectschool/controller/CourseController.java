package com.huysor.projectschool.controller;

import com.huysor.projectschool.dto.course.CoursePostDTO;
import com.huysor.projectschool.dto.course.CourseRequestDTO;
import com.huysor.projectschool.entity.Course;
import com.huysor.projectschool.exception.ApiRequestException;
import com.huysor.projectschool.mapping.CourseMapper;
import com.huysor.projectschool.services.CourseServices;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("api")
@Slf4j
public class CourseController {
    private final CourseServices courseServices;
    private final CourseMapper courseMapper;
    private String url;

    private void setUrl(String url) {
        this.url = url;
    }

    public String getUrl() {
        return ServletUriComponentsBuilder.fromCurrentContextPath().path("api/categories/course/view/").path(url).toUriString();
    }

    @PostMapping(value = "categories/course")
    public ResponseEntity<?> createCourse(@RequestParam(value = "file", required = false) MultipartFile file, @RequestPart("course") CoursePostDTO coursePostDTO) {
        try {
            if (file == null || file.isEmpty()) {
                Course course = courseMapper.toCourse(coursePostDTO);
                course = courseServices.create(course);
                CourseRequestDTO requestDTO = courseMapper.toCourseDTO(course);
                String url = ServletUriComponentsBuilder.fromCurrentContextPath().path("api/categories/course/view/").path("course.jpeg").toUriString();
                requestDTO.setImgUrl(url);
                return ResponseEntity.status(HttpStatus.CREATED).body(requestDTO);
            } else {
                Course course = courseMapper.toCourse(coursePostDTO);
                Course createdCourse = courseServices.createWithImage(file, course);
                CourseRequestDTO courseRequestDTO = courseMapper.toCourseDTO(createdCourse);
                String fileName = createdCourse.getImageName();
                setUrl(fileName);
                courseRequestDTO.setImgUrl(url);
                return ResponseEntity.ok(courseRequestDTO);
            }
        } catch (Exception e) {
            return ResponseEntity.ok(e.getMessage());
        }
    }


    @PutMapping("categories/course/{id}")
    public ResponseEntity<?> updateCourse(@PathVariable("id") Long id, @RequestParam(value = "file", required = false) MultipartFile file, @RequestPart("course") CoursePostDTO courseDTO) {
        if (file == null || file.isEmpty()) {
            Course findCourse = courseServices.findById(id);
            Course course = courseMapper.toCourse(courseDTO);
            course.setId(findCourse.getId());
            course = courseServices.create(course);
            CourseRequestDTO courseRequestDTO = courseMapper.toCourseDTO(course);
            setUrl("course.jpeg");
            courseRequestDTO.setImgUrl(getUrl());
            return ResponseEntity.ok(courseRequestDTO);
        } else {
            Course findCourse = courseServices.findById(id);
            Course course = courseMapper.toCourse(courseDTO);
            course.setId(findCourse.getId());
            if (findCourse.getImageName() == null) {
                course = courseServices.update(file, course);
                setUrl(course.getImageName());
                CourseRequestDTO courseRequestDTO = courseMapper.toCourseDTO(course);
                courseRequestDTO.setImgUrl(getUrl());
                return ResponseEntity.ok(courseRequestDTO);
            }
            else if (findCourse.getImageName().equals(file.getOriginalFilename())) {
                course.setImageName(file.getOriginalFilename());
                course = courseServices.create(course);
                CourseRequestDTO courseRequestDTO = courseMapper.toCourseDTO(course);
                setUrl(course.getImageName());
                courseRequestDTO.setImgUrl(getUrl());
                return ResponseEntity.ok(courseRequestDTO);
            } else {

                course = courseServices.update(file, course);
                CourseRequestDTO courseRequestDTO = courseMapper.toCourseDTO(course);
                setUrl(courseRequestDTO.getImgUrl());
                courseRequestDTO.setImgUrl(getUrl());
             return    ResponseEntity.ok(courseRequestDTO);
            }
        }
        }

        @GetMapping("categories/course")
        public ResponseEntity<?> getAllCourse () {
            List<Course> courses = courseServices.findAll();
            List<CourseRequestDTO> courseRequestDTO = courses
                    .stream()
                    .map(courseMapper::toCourseDTO)
                    .map(courseRequestDTO1 -> {
                        String imageName = courseRequestDTO1.getImgUrl();
                        if (imageName == null || imageName.isEmpty()) {
                            String url = ServletUriComponentsBuilder.fromCurrentContextPath().path("api/categories/course/view/").path("course.jpeg").toUriString();
                            courseRequestDTO1.setImgUrl(url);
                            return courseRequestDTO1;
                        }
                        String url = ServletUriComponentsBuilder.fromCurrentContextPath().path("api/categories/course/view/").path(imageName).toUriString();
                        courseRequestDTO1.setImgUrl(url);
                        return courseRequestDTO1;
                    })
                    .collect(Collectors.toList());
            return ResponseEntity.ok(courseRequestDTO);
        }

        @GetMapping("categories/course/{id}")
        public ResponseEntity<?> getCourseById (@PathVariable("id") Long id){
            Course course = courseServices.findById(id);
            String url = ServletUriComponentsBuilder.fromCurrentContextPath().path("api/categories/course/view/").path(course.getImageName()).toUriString();
            CourseRequestDTO requestDTO = courseMapper.toCourseDTO(course);
            if (requestDTO.getImageName() != null) {
                requestDTO.setImgUrl(url);
            }

            return ResponseEntity.ok(requestDTO);
        }

        @GetMapping("categories/course/view/{image}")
        public ResponseEntity<?> viewImage (@PathVariable("image") String image){

            Course fileName = courseServices.findByName(image);

            try {
                String dirtoryName = System.getProperty("user.dir") + "/upload/file";
                if (fileName == null || fileName.getImageName().isEmpty()) {
                    Path filePath = Paths.get(dirtoryName).resolve(image).normalize();
                    Resource resource = new FileSystemResource(filePath);
                    return ResponseEntity.ok()
                            .contentType(MediaType.parseMediaType(Files.probeContentType(filePath)))
                            .body(resource);
                }
                Path filePath = Paths.get(dirtoryName).resolve(fileName.getImageName()).normalize();
                Resource resource = new FileSystemResource(filePath);
                if (resource.exists()) {
                    return ResponseEntity.ok()
                            .contentType(MediaType.parseMediaType(Files.probeContentType(filePath)))
                            .body(resource);
                } else {
                    return null;
                }
            } catch (Exception e) {
                log.error(e.getMessage());
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
            }

        }

        @GetMapping("categories/course/v1/{image}")
        public ResponseEntity<?> getFile (@PathVariable("image") String name) throws IOException {

            Course course = courseServices.findByName(name);
            String uploadDir = System.getProperty("user.dir") + "/upload/file";
            Path filePath = Paths.get(uploadDir).resolve(course.getImageName()).normalize();
            Resource resource = new UrlResource(filePath.toUri());
            String contentType = Files.probeContentType(filePath);
            if (contentType == null) {
                contentType = "application/octet-stream";
            }
            return ResponseEntity.ok()
                    .contentType(MediaType.parseMediaType(contentType))
                    .body(resource);
        }


        @GetMapping("{id}/course/")
        public ResponseEntity<?> getCourseByCourseId (@PathVariable("id") Long id){
            List<Course> courses = courseServices.findByCategoryId(id);
            if (courses.isEmpty()) {
                throw new ApiRequestException("Courses not found for the given category ID.");
            }
            List<CourseRequestDTO> courseRequestDTOS = courses.stream().map(courseMapper::toCourseDTO).collect(Collectors.toList());
            return ResponseEntity.ok(courseRequestDTOS);
        }

        @DeleteMapping("categories/course/{id}")
        public ResponseEntity<?> deleteCourse (@PathVariable("id") Long id){
            courseServices.delete(id);
            String msg = "Cate with id :" + id + " has been deleted";
            HttpStatus status = HttpStatus.NO_CONTENT;
            return new ResponseEntity<>(msg, status);
        }


    }
