package com.huysor.projectschool.services;

import com.huysor.projectschool.entity.Students;

import java.util.List;

public interface StudentServices {
    List<Students> allStudent();
    Students findStudentById(Long id);
    Students updateStudent(Long id,Students students);
    Students createStudent(Students students);
    void deleteStudent(Long id);

}
