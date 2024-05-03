package com.huysor.projectschool.services;

import com.huysor.projectschool.dto.category.CategoryRequestDTO;
import com.huysor.projectschool.entity.Category;

import java.util.List;

public interface CategoryServices {
    List<Category> allCategory();

    Category create(Category category);

    Category update(Long id, Category category);

    void delete(Long id);

    Category getCategoryById(Long id);
}
