package com.huysor.projectschool.repo;


import com.huysor.projectschool.entity.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;

public interface CategoryRepo extends JpaRepository<Category,Long> {
    Page<Category> findByCreateTimeAfterAndUpdateTimeBefore(
            LocalDateTime createStartDate,
            LocalDateTime updateEndDate,
            Pageable pageable
    );
}
