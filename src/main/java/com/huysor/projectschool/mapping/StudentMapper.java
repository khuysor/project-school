package com.huysor.projectschool.mapping;

import com.huysor.projectschool.dto.students.StudentCreateDTO;
import com.huysor.projectschool.entity.Students;
import com.huysor.projectschool.enums.GenderEnum;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

@Mapper
public interface StudentMapper {
    StudentMapper INSTANCE= Mappers.getMapper(StudentMapper.class);
    StudentCreateDTO toStudentDTO(Students students);

    @Mapping(source = "gender", target = "gender", qualifiedByName = "stringToGenderEnum")
    Students toStudents(StudentCreateDTO studentCreateDTO);

    @Named("stringToGenderEnum")
    default GenderEnum stringToGenderEnum(String gender) {
        return GenderEnum.fromValue(gender);
    }

    @Named("genderEnumToString")
    default String genderEnumToString(GenderEnum gender) {
        return gender == null ? null : gender.name();
    }
}
