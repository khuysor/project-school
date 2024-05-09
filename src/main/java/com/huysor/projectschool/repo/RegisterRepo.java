package com.huysor.projectschool.repo;

import com.huysor.projectschool.entity.Register;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RegisterRepo extends JpaRepository<Register,Long> {
}
