package com.huysor.projectschool.repo;

import com.huysor.projectschool.entity.Students;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;

public interface StudentRepo extends JpaRepository<Students,Long> {
    Page<Students> findByCreateTimeAfterAndUpdateTimeBefore(
            LocalDateTime createStartDate,
            LocalDateTime updateEndDate,
            Pageable pageable
    );
}
