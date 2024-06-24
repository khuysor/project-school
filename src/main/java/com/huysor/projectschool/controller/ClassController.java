package com.huysor.projectschool.controller;

import com.huysor.projectschool.dto.category.CategoryPostDTO;
import com.huysor.projectschool.dto.category.CategoryRequestDTO;
import com.huysor.projectschool.dto.category.CategoryWithCourse;
import com.huysor.projectschool.entity.Category;
import com.huysor.projectschool.mapping.CategoryMapper;
import com.huysor.projectschool.services.CategoryServices;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api/class")
@RequiredArgsConstructor
public class ClassController {
    private final CategoryServices categoryServices;
    private final CategoryMapper categoryMapper;


    @GetMapping
    public ResponseEntity<?> allCategory() {
        List<CategoryRequestDTO> categoryRequestDTOS
                = categoryServices.allCategory()
                .stream()
                .map(categoryMapper::toCateRequest)
                .collect(Collectors.toList());
        return ResponseEntity.ok(categoryRequestDTOS);
    }

    @GetMapping("/course")
    public ResponseEntity<?> allCourseByCategory() {
        List<Category> categories = categoryServices.allCategory();
        List<CategoryWithCourse> categoryWithCourse = categories.stream().map(categoryMapper::toCategoryWithCourse).toList();
        return ResponseEntity.ok(categoryWithCourse);
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody CategoryPostDTO categoryPostDTO) {
        Category category = CategoryMapper.INSTANCE.toCategoryPost(categoryPostDTO);
        return ResponseEntity.ok(categoryServices.create(category));
    }

    @GetMapping("{id}")
    public ResponseEntity<?> getById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(categoryServices.getCategoryById(id));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> deleteByid(@PathVariable("id") Long id) {
        categoryServices.delete(id);
        String msg = "Cate with id :" + id + " has been deleted";
        HttpStatus status = HttpStatus.NO_CONTENT;
        return new ResponseEntity<>(msg, status);
    }

    @PutMapping("{id}")
    public ResponseEntity<?> update(@PathVariable("id") Long id, @RequestBody CategoryPostDTO categoryDTO) {
        Category category = CategoryMapper.INSTANCE.toCategoryPost(categoryDTO);
        category = categoryServices.update(id, category);
        return ResponseEntity.ok(category);
    }
}
