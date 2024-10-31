package com.huysor.projectschool.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
@Data
@Entity
@Table(name = "tbl_course")
public class Course extends AuditEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String courseName;
    private String teacherName;
    private String description;
    private BigDecimal price;
    @Column(nullable = true)
    private String imageName;
    @ManyToOne
    @JoinColumn(name = "category_id",nullable = false)
    @JsonBackReference
    private Category category;



}
