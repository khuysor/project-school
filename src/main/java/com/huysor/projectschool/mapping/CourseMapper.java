package com.huysor.projectschool.mapping;


import com.huysor.projectschool.dto.CourseDTO;
import com.huysor.projectschool.entity.Course;
import com.huysor.projectschool.services.CategoryServices;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;


@Mapper(componentModel ="spring",uses = {CategoryServices.class})
public interface CourseMapper {
    @Mapping(source = "cateId",target = "category")
    Course courseToCourse(CourseDTO courseDTO);
}
