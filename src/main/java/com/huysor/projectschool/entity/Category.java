package com.huysor.projectschool.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
@Table (name = "tbl_category")
public class Category extends AuditEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String code;
    private String name;
    @OneToMany(mappedBy = "category")
    @JsonManagedReference
    private List<Course>courses;

}

