package com.huysor.projectschool.mapping;

import com.huysor.projectschool.dto.request.StudentReq;
import com.huysor.projectschool.dto.response.StudentResp;
import com.huysor.projectschool.entity.Students;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
@Mapper(componentModel = "spring")
public interface StudentMapper {
    @Mapping(target = "createTime", ignore = true)
    @Mapping(target = "updateTime", ignore = true)
    void updateEntityFromDto(StudentReq studentReq, @MappingTarget Students students);

    Students toStudents(StudentReq studentReq);
    StudentResp toStudentResp(Students students);
}
