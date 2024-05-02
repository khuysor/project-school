package com.huysor.projectschool.controller;

import com.huysor.projectschool.dto.CategoryDTO;
import com.huysor.projectschool.entity.Category;
import com.huysor.projectschool.mapping.CategoryMapper;
import com.huysor.projectschool.services.CategoryServices;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/category")
@RequiredArgsConstructor
public class CategoryController {
   private final CategoryServices categoryServices;
    @PostMapping
    public ResponseEntity<?>create(@RequestBody  CategoryDTO categoryDTO) {
        Category category= CategoryMapper.INSTANCE.toCategory(categoryDTO);
        return ResponseEntity.ok(categoryServices.create(category));
    }
    @GetMapping("{id}")
    public ResponseEntity<?>getById(@PathVariable("id")Long id){
        return ResponseEntity.ok(categoryServices.getCategoryById(id));
    }

   @DeleteMapping("{id}")
    public ResponseEntity<?>deleteByid(@PathVariable Long id){
        categoryServices.delete(id);
        String msg= "Cate with id :"+id+" has been deleted";
       HttpStatus status= HttpStatus.NO_CONTENT;
        return new ResponseEntity<>(msg,status);
    }
}
