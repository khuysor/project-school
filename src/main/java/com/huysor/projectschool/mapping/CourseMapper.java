package com.huysor.projectschool.mapping;


import com.huysor.projectschool.dto.course.CoursePostDTO;
import com.huysor.projectschool.dto.course.CourseRequestDTO;
import com.huysor.projectschool.entity.Course;
import com.huysor.projectschool.services.CategoryServices;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;


@Mapper(componentModel = "spring", uses = {CategoryServices.class})
public interface CourseMapper {
    //    CourseMapper INSTANCE= Mappers.getMapper(CourseMapper.class);
    @Mapping(target = "category", source = "cateId")
    Course toCourse(CoursePostDTO courseDTO);

    CourseRequestDTO toCourseDTO(Course course);



}
