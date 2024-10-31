package com.huysor.projectschool.mapping;

import com.huysor.projectschool.dto.request.CategoryReq;
import com.huysor.projectschool.dto.response.CategoryResp;
import com.huysor.projectschool.entity.Category;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface CategoryMapper {

    @Mapping(source = "categoryName", target = "name")
    Category toCategory(CategoryReq categoryReq);

    @Mapping(source = "name", target = "categoryName")
    CategoryResp toCategoryResp(Category category);


    @Mapping(target = "createTime", ignore = true)
    @Mapping(target = "updateTime", ignore = true)
    @Mapping(target = "name",source = "categoryName")
    @Mapping(target = "code",source = "code")
    void updateEntityFromDto(CategoryReq categoryReq, @MappingTarget Category cate);
}
