package com.huysor.projectschool.repo;


import com.huysor.projectschool.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CourseRepo extends JpaRepository<Course,Long> {
   List< Course> findCourseByCategoryId(Long id);
   @Query("select c.imageName from Course c where c.imageName = :name")
   String findImageName(String name);

 Course findByImageName(String name);

}
