package com.huysor.projectschool.controller;

import com.huysor.projectschool.constant.ApiConstant;
import com.huysor.projectschool.dto.request.CourseReq;
import com.huysor.projectschool.dto.request.FilterDate;
import com.huysor.projectschool.dto.request.PageReq;
import com.huysor.projectschool.dto.response.ResultResp;
import com.huysor.projectschool.services.CourseServices;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import static com.huysor.projectschool.utils.DateUtil.parseAndValidateDate;

@RestController

@RequiredArgsConstructor
public class CourseController {
    private final CourseServices courseServices;

    @PostMapping(value = ApiConstant.ApiCourse,consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResultResp saveOrUpdate(@RequestPart(value = "file", required = false) MultipartFile file, @RequestPart("course") CourseReq courseReq) {
        return courseServices.createWithImage(file, courseReq);
    }

    @GetMapping(ApiConstant.ApiCourse)
    public ResultResp allCourse(@RequestParam(value = "_pageIndex", defaultValue = "1") int pageIndex,
                                @RequestParam(value = "_pageSize", defaultValue = "25") int pageSize,
                                @RequestParam(value = "startDate", required = false) String startDate,
                                @RequestParam(value = "endDate", required = false) String endDate) {
        PageReq pageReq = new PageReq(pageIndex, pageSize);
        FilterDate filterDate=new FilterDate();
        filterDate.setStartDate(parseAndValidateDate(startDate));
        filterDate.setEndDate( parseAndValidateDate(endDate));
        return courseServices.findAll(pageReq,filterDate);
    }
    @GetMapping(ApiConstant.ApiCourseCount)
    public ResultResp countCourse(){
        return courseServices.countCourse();
    }
}
