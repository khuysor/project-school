package com.huysor.projectschool.entity;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.Null;
import lombok.Data;
import org.hibernate.annotations.ColumnDefault;

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
    @Column(nullable = true)
    private byte fileByte [];
    @Column(nullable = true)
    private String imageName;
    @Column(updatable = false)
    private LocalDateTime createTime=LocalDateTime.now();
    private LocalDateTime updateTime=LocalDateTime.now();

    @ManyToOne
    @JoinColumn(name = "cate_id")
    @JsonBackReference
    private Category category;



}
