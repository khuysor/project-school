package com.huysor.projectschool.repo;

import com.huysor.projectschool.entity.Category;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
public class CateRepoTest {
    @Autowired
    private CategoryRepo categoryRepo;
    @Test
    public void testCreate(){
        //given
        Category category= new Category();
        category.setName("Apple");
        category.setCode("123");
        category.setCreateTime(LocalDateTime.of(2024,02,01,12,39,12));
        category.setUpdateTime(LocalDateTime.of(2024,02,01,12,39,12));
        Category category1= new Category();
        category1.setName("Samsung");
        category.setCode("1234");
        category.setCreateTime(LocalDateTime.of(2024,02,01,12,39,12));
        category.setUpdateTime(LocalDateTime.of(2024,02,01,12,39,12));

        categoryRepo.save(category);
        categoryRepo.save(category1);
        //when
        List<Category> categories = categoryRepo.findAll();
        //then

        assertEquals(2,categories.size());
        assertEquals("Apple",categories.get(0).getName());
        assertEquals(1,categories.get(0).getId());

    }
}
