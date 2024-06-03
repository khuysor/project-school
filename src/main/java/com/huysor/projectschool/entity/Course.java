package com.huysor.projectschool.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
@Entity
@Table(name = "tbl_course")
@Data
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String courseName;
    private String teacherName;
    private String description;
    private BigDecimal price;
    @Column(updatable = false)
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
    @ManyToOne
    @JoinColumn(name = "cate_id")
    @JsonBackReference
    private Category category;
}
