package com.huysor.projectschool.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Entity
@Table (name = "tbl_category")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String code;
    private String name;
    @Column(updatable = false)
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
    @OneToMany(mappedBy = "category")
    @JsonManagedReference
    private List<Course>courses;
}

