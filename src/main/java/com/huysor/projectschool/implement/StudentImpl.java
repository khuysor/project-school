package com.huysor.projectschool.implement;

import com.huysor.projectschool.entity.Students;
import com.huysor.projectschool.exception.ApiRequestException;
import com.huysor.projectschool.repo.StudenRepo;
import com.huysor.projectschool.services.StudentServices;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StudentImpl implements StudentServices {
    private final StudenRepo studenRepo;

    @Override
    public List<Students> allStudent() {
        List<Students> students = studenRepo.findAll();
        return students;
    }

    @Override
    public Students findStudentById(Long id) {
        return  studenRepo.findById(id).orElseThrow(()-> new ApiRequestException("student with id : %d not found".valueOf(id) ));
    }

    @Override
    public Students updateStudent(Long id, Students students) {
        findStudentById(id);
        students.setId(id);
        return studenRepo.save(students);
    }

    @Override
    public Students createStudent(Students students) {
        return studenRepo.save(students);
    }

    @Override
    public void deleteStudent(Long id) {
       Students students= findStudentById(id);
       studenRepo.delete(students);
    }
}
