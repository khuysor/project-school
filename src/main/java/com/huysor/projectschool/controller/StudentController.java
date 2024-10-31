package com.huysor.projectschool.controller;

import com.huysor.projectschool.constant.ApiConstant;
import com.huysor.projectschool.dto.request.FilterDate;
import com.huysor.projectschool.dto.request.PageReq;
import com.huysor.projectschool.dto.request.StudentReq;
import com.huysor.projectschool.dto.response.ResultResp;
import com.huysor.projectschool.services.StudentServices;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import static com.huysor.projectschool.utils.DateUtil.parseAndValidateDate;

@RestController
@RequiredArgsConstructor
public class StudentController {
    private final StudentServices studentServices;

    @PostMapping(ApiConstant.ApiStudent)
    public ResultResp saveOrUpdate(@RequestBody StudentReq studentReq) {
        return studentServices.addStudent(studentReq);
    }

    @GetMapping(ApiConstant.ApiStudent)
    public ResultResp getAllStudent(@RequestParam(value = "_pageIndex", defaultValue = "1") int pageIndex,
                                    @RequestParam(value = "_pageSize", defaultValue = "25") int pageSize,
                                    @RequestParam(value = "startDate", required = false) String startDate,
                                    @RequestParam(value = "endDate", required = false) String endDate) {
        FilterDate filterDate = new FilterDate();
        filterDate.setStartDate(parseAndValidateDate(startDate));
        filterDate.setEndDate(parseAndValidateDate(endDate));
        PageReq pageReq = new PageReq(pageIndex, pageSize);
        return studentServices.allStudentWithPage(pageReq, filterDate);
    }

    @GetMapping(ApiConstant.ApiStudentCount)
    public ResultResp countStudent() {
        return studentServices.countStudent();
    }

    @GetMapping(ApiConstant.ApiStudentAll)
    public ResultResp getAllStudent() {
        return studentServices.allStudents();
    }

}
