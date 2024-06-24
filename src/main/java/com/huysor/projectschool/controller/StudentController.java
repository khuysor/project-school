package com.huysor.projectschool.controller;

import com.huysor.projectschool.dto.students.StudentCreateDTO;
import com.huysor.projectschool.entity.Students;
import com.huysor.projectschool.mapping.StudentMapper;
import com.huysor.projectschool.services.StudentServices;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("api/students")
@PreAuthorize("hasRole('ADMIN')")
public class StudentController {
    private final StudentServices studentServices;

    @PostMapping
    public ResponseEntity<?> createStudent(@RequestBody StudentCreateDTO studentCreateDTO) {
        Students students = StudentMapper.INSTANCE.toStudents(studentCreateDTO);
        studentServices.createStudent(students);
        return ResponseEntity.ok(students);
    }

    @GetMapping
    public ResponseEntity<?> getStudents() {
        List<Students> students = studentServices.allStudent();
        return ResponseEntity.ok(students);
    }

    @GetMapping("{id}")
    public ResponseEntity<?> getStudentById(@RequestParam("id") Long id) {
        Students students = studentServices.findStudentById(id);
        return ResponseEntity.ok(students);
    }

    @PutMapping("{id}")
    public ResponseEntity<?> updateStudent(@PathVariable("id") Long id, @RequestBody StudentCreateDTO studentCreateDTO) {
        Students students = StudentMapper.INSTANCE.toStudents(studentCreateDTO);
        studentServices.updateStudent(id, students);
        return ResponseEntity.ok(students);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> deleteStudent(@PathVariable("id") Long id) {
        studentServices.deleteStudent(id);
        return ResponseEntity.status(HttpStatus.OK).body("Student deleted successfully");
    }
}
