package com.huysor.projectschool.repo;

import com.huysor.projectschool.controller.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepo extends JpaRepository<Category,Long> {
}
