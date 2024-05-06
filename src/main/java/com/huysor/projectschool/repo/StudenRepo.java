package com.huysor.projectschool.repo;

import com.huysor.projectschool.entity.Students;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudenRepo extends JpaRepository<Students,Long> {

}
