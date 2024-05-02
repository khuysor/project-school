package com.huysor.projectschool.mapping;


import com.huysor.projectschool.dto.CourseDTO;
import com.huysor.projectschool.entity.Course;
import com.huysor.projectschool.services.CategoryServices;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;


@Mapper(componentModel ="spring",uses = {CategoryServices.class})
public interface CourseMapper {
//    CourseMapper INSTANCE= Mappers.getMapper(CourseMapper.class);
    @Mapping(target = "category",source = "cateId")
   Course toCourse(CourseDTO courseDTO);
    @Mapping(source = "category.id",target = "cateId")
    CourseDTO toCourseDTO(Course course);

}
