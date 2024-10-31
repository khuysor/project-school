package com.huysor.projectschool.services;

import com.huysor.projectschool.dto.request.CourseReq;
import com.huysor.projectschool.dto.request.FilterDate;
import com.huysor.projectschool.dto.request.PageReq;
import com.huysor.projectschool.dto.response.ResultResp;
import com.huysor.projectschool.entity.Course;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface CourseServices {
    ResultResp findAll(PageReq pageReq, FilterDate filterDate);

    Course findById(Long id);

    ResultResp createWithImage(MultipartFile file, CourseReq courseReq);

    List<Course> findByCategoryId(Long cateId);

    void delete(Long id);

    Course findByName(String name);
    ResultResp countCourse();
}
