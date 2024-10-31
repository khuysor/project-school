package com.huysor.projectschool.services.implement;

import com.huysor.projectschool.dto.request.FilterDate;
import com.huysor.projectschool.dto.request.PageReq;
import com.huysor.projectschool.dto.request.StudentReq;
import com.huysor.projectschool.dto.response.ResultResp;
import com.huysor.projectschool.dto.response.StudentResp;
import com.huysor.projectschool.entity.Students;
import com.huysor.projectschool.mapping.StudentMapper;
import com.huysor.projectschool.repo.StudentRepo;
import com.huysor.projectschool.services.StudentServices;
import com.huysor.projectschool.utils.Pagination;
import com.huysor.projectschool.utils.ResponseData;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class StudentImpl implements StudentServices {
    private final StudentMapper studentMapper;
    private final StudentRepo studentRepo;

    @Override
    public ResultResp allStudents() {
        return ResultResp.success(studentRepo.findAll().stream().map(studentMapper::toStudentResp).toList());
    }

    @Override
    public ResultResp addStudent(StudentReq studentReq) {
        Students student;
        if (studentReq.getId() == null) {
            student = studentMapper.toStudents(studentReq);
            try {
                studentRepo.save(student);
                log.info("create student success {}", student);
                return ResultResp.success();
            } catch (Exception e) {
                log.error("add student error {}", studentReq);
                return ResultResp.fail();
            }
        } else {
            Optional<Students> existingStudent = studentRepo.findById(studentReq.getId());
            if (existingStudent.isEmpty()) {
                log.error("can't not find student with id : {}", studentReq.getId());
                return ResultResp.fail("can't not find student with id : " + studentReq.getId());
            }
            try {
                studentMapper.updateEntityFromDto(studentReq, existingStudent.get());
                studentRepo.save(existingStudent.get());
                log.info("update student success {}", existingStudent.get());
                return ResultResp.success();
            } catch (Exception e) {
                log.error("update student error {}", studentReq);
                return ResultResp.fail();
            }
        }
    }

    @Override
    public ResultResp allStudentWithPage(PageReq pageReq, FilterDate filterDate) {
        Pageable pageable = PageRequest.of(pageReq.getPageIndex()-1, pageReq.getPageSize(), Sort.by(Sort.Direction.DESC, "id"));
        Page<Students> studentsPage;
        if (filterDate.getEndDate()==null|| filterDate.getStartDate()==null) {
            studentsPage = studentRepo.findAll(pageable);
        }else{
            studentsPage=studentRepo.findByCreateTimeAfterAndUpdateTimeBefore(filterDate.getStartDate(),filterDate.getEndDate(),pageable);
        }
        List<StudentResp> studentResp=studentsPage.getContent().stream().map(studentMapper::toStudentResp).toList();
        return ResultResp.success(new ResponseData<StudentResp>(studentResp,new Pagination(
                studentsPage.getNumber() + 1, // Adjust for 1-based index if needed
                studentsPage.getSize(),
                studentsPage.getTotalElements(),
                studentsPage.getTotalPages(),
                studentsPage.hasNext(),
                studentsPage.hasPrevious()
        )));

    }

    @Override
    public ResultResp countStudent() {
        return ResultResp.success(studentRepo.count());
    }


}
