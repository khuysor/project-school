package com.huysor.projectschool.mapping;

import com.huysor.projectschool.dto.students.StudentCreateDTO;
import com.huysor.projectschool.entity.Students;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface StudentMapper {
    StudentMapper INSTANCE= Mappers.getMapper(StudentMapper.class);
    StudentCreateDTO toStudentDTO(Students students);
    Students toStudents(StudentCreateDTO studentCreateDTO);
}
