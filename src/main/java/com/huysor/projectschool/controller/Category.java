package com.huysor.projectschool.controller;

import com.huysor.projectschool.dto.CategoryDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/category")
public class Category {
    @PostMapping
    public ResponseEntity<?>create(@RequestBody  CategoryDTO categoryDTO) {
        return ResponseEntity.ok(categoryDTO);
    }
}
