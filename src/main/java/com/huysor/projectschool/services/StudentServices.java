package com.huysor.projectschool.services;

import com.huysor.projectschool.dto.request.FilterDate;
import com.huysor.projectschool.dto.request.PageReq;
import com.huysor.projectschool.dto.request.StudentReq;
import com.huysor.projectschool.dto.response.ResultResp;

public interface StudentServices {
    ResultResp allStudents();
    ResultResp addStudent(StudentReq studentReq);
    ResultResp allStudentWithPage(PageReq pageReq, FilterDate filterDate);
    ResultResp countStudent();

}
