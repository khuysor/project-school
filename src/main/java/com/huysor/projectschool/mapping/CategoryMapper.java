package com.huysor.projectschool.mapping;

import com.huysor.projectschool.dto.category.CategoryPostDTO;
import com.huysor.projectschool.dto.category.CategoryRequestDTO;
import com.huysor.projectschool.dto.category.CategoryWithCourse;
import com.huysor.projectschool.entity.Category;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface CategoryMapper {
    CategoryMapper INSTANCE = Mappers.getMapper(CategoryMapper.class);

    CategoryRequestDTO toCateRequest(Category category);

    @Mapping(target = "id", ignore = true)
    Category toCategoryPost(CategoryPostDTO categoryPostDTO);

    @Mapping(target = "coursesList", source = "courses")
    CategoryWithCourse toCategoryWithCourse(Category category);
}
