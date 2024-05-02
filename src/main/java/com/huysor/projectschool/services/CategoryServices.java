package com.huysor.projectschool.services;

import com.huysor.projectschool.dto.CategoryDTO;
import com.huysor.projectschool.entity.Category;

import java.util.List;

public interface CategoryServices {
    List<CategoryDTO> allCategory();

    Category create(Category category);

    Category update(Long id, CategoryDTO categoryDTO);

    void delete(Long id);

    Category getCategoryById(Long id);
}
