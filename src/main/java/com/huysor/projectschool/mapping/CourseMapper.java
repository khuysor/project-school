package com.huysor.projectschool.mapping;

import com.huysor.projectschool.dto.request.CourseReq;
import com.huysor.projectschool.dto.response.CourseResp;
import com.huysor.projectschool.entity.Course;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface CourseMapper {
    @Mapping(target = "createTime", ignore = true)
    Course toCourse(CourseReq req);


    @Mapping(source = "createTime", target = "createTime")
    @Mapping(source = "updateTime", target = "updateTime")
    @Mapping(source = "category.name",target = "categoryName")
    CourseResp toCourseResp(Course course);



    @Mapping(target = "createTime", ignore = true) // Ignore createTime on updates
    @Mapping(target = "updateTime", ignore = true) // Automatically map updateTime if needed
    void updateEntityFromDto(CourseReq courseReq, @MappingTarget Course course);
}
