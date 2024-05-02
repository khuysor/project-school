package com.huysor.projectschool.repo;


import com.huysor.projectschool.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepo extends JpaRepository<Category,Long> {
}
