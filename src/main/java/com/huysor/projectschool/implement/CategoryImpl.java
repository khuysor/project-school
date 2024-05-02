package com.huysor.projectschool.implement;

import com.huysor.projectschool.controller.CategoryController;
import com.huysor.projectschool.dto.CategoryDTO;
import com.huysor.projectschool.entity.Category;
import com.huysor.projectschool.exception.ApiRequestException;
import com.huysor.projectschool.mapping.CategoryMapper;
import com.huysor.projectschool.repo.CategoryRepo;
import com.huysor.projectschool.services.CategoryServices;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CategoryImpl implements CategoryServices {
    private final CategoryRepo categoryRepo;

    @Override
    public List<CategoryDTO> allCategory() {
        List<Category> categories = categoryRepo.findAll();
        List<CategoryDTO> catedto = categories.stream()
                .map(cate -> CategoryMapper.INSTANCE.toCategoryDTO(cate)).collect(Collectors.toList());
        return catedto;
    }

    @Override
    public Category create(Category category) {
        return categoryRepo.save(category);
    }

    @Override
    public Category update(Long id, Category cate) {
          Category category = getCategoryById(id);
          cate.setId(category.getId());
        return categoryRepo.save(cate);
    }

    @Override
    public void delete(Long id) {
        Category category = getCategoryById(id);
        categoryRepo.delete(category);

    }

    @Override
    public Category getCategoryById(Long id) {
        return categoryRepo.findById(id).orElseThrow(() -> new ApiRequestException("Cate with id : " + id + " not found"));

    }
}
